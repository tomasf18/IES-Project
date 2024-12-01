package sts.backend.core_app.services.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import sts.backend.core_app.models.EndpointsEntity;
import sts.backend.core_app.models.Team;

import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {
    
    private final ElasticsearchOperations elasticsearchOperations;
    private final RelationalQueries relationalQueries;

    private final IndexCoordinates sensorsIndex;
    private final IndexCoordinates endpointsIndex;

    public ElasticSearchAnalysisImpl(ElasticsearchOperations elasticsearchOperations, RelationalQueries relationalQueries) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.relationalQueries = relationalQueries;
        this.sensorsIndex = elasticsearchOperations.getIndexCoordinatesFor(SensorsLogEntity.class);
        this.endpointsIndex = elasticsearchOperations.getIndexCoordinatesFor(EndpointsEntity.class);
    }

    public List<SensorsLogEntity> getLogs() {
        // TODO: remove this method (only to demonstrate how to read data from Elasticsearch)
        long now = System.currentTimeMillis();
        long oneMinutesAgo = now - 5 * 60 * 1000;

        Criteria criteria = new Criteria("timestamp").greaterThanEqual(oneMinutesAgo);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHis = elasticsearchOperations.search(query, SensorsLogEntity.class, sensorsIndex);
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
        SearchHits<SensorsLogEntity> searchHits = elasticsearchOperations.search(query, SensorsLogEntity.class, sensorsIndex);

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
            if (logsCount >= 1000) {          // threshold for critical state
                state = "critical";
            } else if (logsCount >= 500) {    // threshold for flooded state
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


    public void addSensorsLog(SensorsLogEntity sensorsLogEntity) {
        elasticsearchOperations.save(sensorsLogEntity, sensorsIndex);
    }
}
