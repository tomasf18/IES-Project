package sts.backend.core_app.services.analysis;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.models.Team;

import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.dto.admin.SensorsLast5Days;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;
import sts.backend.core_app.consumer.interfaces.WebSocketController;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {

    private final ElasticsearchOperations elasticsearchOperations;
    private final RelationalQueries relationalQueries;
    private final IndexCoordinates sensorsIndex;
    private final WebSocketController webSocketController;

    public ElasticSearchAnalysisImpl(ElasticsearchOperations elasticsearchOperations,
            RelationalQueries relationalQueries, WebSocketController webSocketController) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.relationalQueries = relationalQueries;
        this.sensorsIndex = elasticsearchOperations.getIndexCoordinatesFor(SensorsLogEntity.class);
        this.webSocketController = webSocketController;
    }

    public List<SensorsLogEntity> getLogs() {
        // TODO: remove this method (only to demonstrate how to read data from
        // Elasticsearch)
        long now = System.currentTimeMillis();
        long oneMinutesAgo = now - 5 * 60 * 1000;

        Criteria criteria = new Criteria("timestamp").greaterThanEqual(oneMinutesAgo);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHis = elasticsearchOperations.search(query, SensorsLogEntity.class,
                sensorsIndex);
        return searchHis.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    // get the sensors logs by team in the week (5 days)
    public List<SensorsTeamWeek> getSensors() {
        long now = System.currentTimeMillis();

        // get the last 5 days
        long oneDay = 24 * 60 * 60 * 1000;
        long fiveDaysAgo = now - 5 * oneDay;

        // criteria for the last 5 days
        Criteria criteria = new Criteria("timestamp").greaterThanEqual(fiveDaysAgo);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHits = elasticsearchOperations.search(query, SensorsLogEntity.class,
                sensorsIndex);

        // group logs by team
        Map<Long, List<SensorsLogEntity>> logsByTeam = searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.groupingBy(SensorsLogEntity::getTeamId));

        // Result list
        List<SensorsTeamWeek> result = new ArrayList<>();
        logsByTeam.forEach((teamId, logs) -> {
            // number of logs
            int logsCount = logs.size();

            String state;
            if (logsCount >= 1000) { // threshold for critical state
                state = "critical";
            } else if (logsCount >= 500) { // threshold for flooded state
                state = "flooded";
            } else {
                state = "normal";
            }

            // get team name by teamId
            String teamName = "";
            try {
                Team team = relationalQueries.getTeamById(teamId);
                teamName = team.getName();
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }

            // add to result list
            result.add(new SensorsTeamWeek(teamName, state, logsCount));
        });

        return result;
    }

    // get the sensors logs in the last 5 days
    public List<SensorsLast5Days> getSensorsLast5Days() {
        long now = System.currentTimeMillis();

        // get the last 5 days
        long oneDay = 24 * 60 * 60 * 1000;
        long fiveDaysAgo = now - 5 * oneDay;

        // criteria for the last 5 days
        Criteria criteria = new Criteria("timestamp").greaterThanEqual(fiveDaysAgo);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHits = elasticsearchOperations.search(query, SensorsLogEntity.class,
                sensorsIndex);

        // group logs by day
        Map<LocalDate, Long> logsByDay = searchHits.stream()
                .map(SearchHit::getContent)
                .map(log -> Instant.ofEpochMilli(log.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate())
                .collect(Collectors.groupingBy(date -> date, Collectors.counting()));

        // get the last 5 days
        List<LocalDate> last5Days = IntStream.rangeClosed(0, 4)
                .mapToObj(i -> LocalDate.now().minusDays(i))
                .sorted()
                .collect(Collectors.toList());

        // Result list
        List<SensorsLast5Days> result = new ArrayList<>();
        for (LocalDate date : last5Days) {
            int logsCount = logsByDay.getOrDefault(date, 0L).intValue();
            result.add(new SensorsLast5Days(date, logsCount));
        }

        return result;
    }

    public Map<String, Integer> getSensorsDay(String date) {
        // Parse the date
        LocalDate localDate = LocalDate.parse(date);
        long startOfDay = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endOfDay = localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Criteria for the day
        Criteria criteria = new Criteria("timestamp").greaterThanEqual(startOfDay).and("timestamp").lessThan(endOfDay);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHits = elasticsearchOperations.search(query, SensorsLogEntity.class,
                sensorsIndex);

        // Group logs by 10-second intervals
        Map<String, Long> logsByInterval = searchHits.stream()
                .map(SearchHit::getContent)
                .map(log -> {
                    long timestamp = log.getTimestamp();
                    long intervalStart = (timestamp / 10000) * 10000; // Find the start of the 10-second interval
                    return Instant.ofEpochMilli(intervalStart).atZone(ZoneId.systemDefault()).toLocalTime();
                })
                .collect(Collectors.groupingBy(
                        time -> String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond()), // Format
                                                                                                                     // as
                                                                                                                     // HH:mm:ss
                        Collectors.counting()));

        // Convert to TreeMap<String, Integer> to ensure ordering by keys
        return logsByInterval.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().intValue(),
                        (e1, e2) -> e1,
                        TreeMap::new));
    }

    private volatile long lastSensorsDaySentTime = 0;

    public void addSensorsLog(SensorsLogEntity sensorsLogEntity) {
        elasticsearchOperations.save(sensorsLogEntity, sensorsIndex);
        // call websocket controller to send the returned valuse of the three functions
        // above
        webSocketController.sendSensorsTeamWeek(getSensors());
        webSocketController.sendSensorsLast5Days(getSensorsLast5Days());
 
        long currentTime = System.currentTimeMillis();
    
        synchronized (this) {
            // Check if the last sent time was less than 10 seconds ago
            if (currentTime - lastSensorsDaySentTime < 10_000) {
                System.out.println("SensorsDay update skipped: Sent less than 10 seconds ago.");
                return;
            }
            // Update the last sent time
            lastSensorsDaySentTime = currentTime;
        }
     
        // Send SensorsDay data
        LocalDate today = LocalDate.now();
        webSocketController.sendSensorsDay(getSensorsDay(today.toString()));
    }
}
