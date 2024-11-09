package sts.backend.core_app.services.analysis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.Duration;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;

@Service
public class RealTimeAnalysisImpl implements RealTimeAnalysis {
    
    private final TimeSeriesQueries timeSeriesQueries;
    private final RelationalQueries relationalQueries;

    public RealTimeAnalysisImpl(RelationalQueries relationalQueries, TimeSeriesQueries timeSeriesQueries) {
        this.relationalQueries = relationalQueries;
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }

    @Override
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        return timeSeriesQueries.getRealTimeExtraDetailsLast24Hours(playerId);
    }

    public RealTimeExtraDetailsResponse getRealTimeExtraDetails(Long sessionId, Long playerId) throws ResourceNotFoundException {
        // Get the session information
        Session session = relationalQueries.getSessionById(sessionId);

        String sessionName = session.getName();
        LocalDateTime startTime = session.getStartTime();
        
        // it's real time, so we need to get the current time
        LocalDateTime endTime = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        String startDate = startTime.format(dateFormatter);
        String date = startDate; // Only display the start: "Oct 12, 2021 12:00"
        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutes();
        int time = (int) minutes;

        // Get the list of players in the session through the PlayerSession table
        List<Long> playerIds = relationalQueries.getPlayerIdsBySessionId(sessionId);
        int participants = playerIds.size();

        // Check if Session is instance of Match
        String opponentTeam = null;
        String matchType = null;
        String location = null;
        String weather = null;
        if (session instanceof Match) {
            Match match = (Match) session;
            opponentTeam = match.getOpponentTeam();
            matchType = match.getType();
            location = match.getLocation();
            weather = match.getWeather();
        }

        // Metrics to retrieve
        List<String> metrics = Arrays.asList("heart_rate", "body_temperature", "respiratory_rate");

        // Get the historical data of each player
        List<RealTimeExtraDetailsPlayer> historicalDataPlayers = new ArrayList<>();

        Double lastHeartRate = 0.0;
        Double lastBodyTemperature = 0.0;
        Double lastRespiratoryRate = 0.0;

        for (Long userId : playerIds) {
            RealTimeExtraDetailsPlayer historicalDataPlayer = new RealTimeExtraDetailsPlayer();
            
            historicalDataPlayer.setPlayerId(userId);
            historicalDataPlayer.setPlayerName(relationalQueries.getPlayerById(userId).getName());
            
            for (String metric : metrics) {
                List<ValueTimeSeriesView> metricData = timeSeriesQueries.getHistoricalData(userId, metric, startTime, endTime);
                switch (metric) {
                    case "heart_rate":
                        historicalDataPlayer.setHeartRateData(metricData);
                        if (userId == playerId && metricData.size() > 0) {
                            lastHeartRate = metricData.get(metricData.size() - 1).getValue();
                        }
                        break;
                    case "body_temperature":
                        historicalDataPlayer.setBodyTemperatureData(metricData);
                        if (userId == playerId && metricData.size() > 0) {
                            lastBodyTemperature = metricData.get(metricData.size() - 1).getValue();
                        }
                        break;
                    case "respiratory_rate":
                        historicalDataPlayer.setRespiratoryRateData(metricData);
                        if (userId == playerId && metricData.size() > 0) {
                            lastRespiratoryRate = metricData.get(metricData.size() - 1).getValue();
                        }
                        break;
                    default:
                        break;
                }

            }
            
            historicalDataPlayers.add(historicalDataPlayer);
        }

        if (session instanceof Match) {
            return new RealTimeExtraDetailsResponse(sessionName, date, time, participants, historicalDataPlayers, lastHeartRate, lastBodyTemperature, lastRespiratoryRate, opponentTeam, matchType, location, weather);
        } 
        return new RealTimeExtraDetailsResponse(sessionName, date, time, participants, historicalDataPlayers, lastHeartRate, lastBodyTemperature, lastRespiratoryRate);
    }
}