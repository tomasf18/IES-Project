package sts.backend.core_app.services.analysis;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.dto.session.HistoricalExtraDetailsResponse;
import sts.backend.core_app.dto.session.HistoricalInfoResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.HistoricalAnalysis;

import sts.backend.core_app.models.Session;

@Service
public class HistoricalAnalysisImpl implements HistoricalAnalysis {

    private final TimeSeriesQueries timeSeriesQueries;
    private final RelationalQueries relationalQueries;

    public HistoricalAnalysisImpl(RelationalQueries relationalQueries, TimeSeriesQueries timeSeriesQueries) {
        this.relationalQueries = relationalQueries;
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public HistoricalExtraDetailsResponse getHistoricalExtraDetails(Long sessionId, Long playerId) throws ResourceNotFoundException {
        // Now in a session we have a list of players, we need to get the historical data of each player
        // Session has a startTime and endTime, we can use these to get the historical data of each player
        // Similar to the getRealTimeExtraDetailsLast24Hours method, we can get the historical data of each player
        // and then return a list of HistoricalInfoResponse objects

        // We can use the relationalQueries to get the list of players in a session
        // and then use the timeSeriesQueries to get the historical data of each player
    
        Session session = relationalQueries.getSessionById(sessionId);

        String sessionName = session.getName();
        LocalDateTime startTime = session.getStartTime();
        LocalDateTime endTime = session.getEndTime();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startDate = startTime.format(dateFormatter);
        String endTimeFormatted = endTime.format(timeFormatter);
        String date = startDate + "-" + endTimeFormatted; // "Oct 12, 2021 12:00-13:30"
        int time = (int) (endTime.getMinute() - startTime.getMinute());

        // Get the list of players in the session through the PlayerSession table
        List<Long> playerIds = relationalQueries.getPlayerIdsBySessionId(sessionId);
        int participants = playerIds.size();

        // Metrics to retrieve
        List<String> metrics = Arrays.asList("heart_rate", "body_temperature", "respiratory_rate");

        // Get the historical data of each player
        List<RealTimeExtraDetailsPlayer> historicalDataPlayers = new ArrayList<>();

        Double averageHeartRate = 0.0;
        Double averageBodyTemperature = 0.0;
        Double averageRespiratoryRate = 0.0;

        for (Long userId : playerIds) {
            RealTimeExtraDetailsPlayer historicalDataPlayer = new RealTimeExtraDetailsPlayer();
            
            historicalDataPlayer.setPlayerId(userId);
            historicalDataPlayer.setPlayerName(relationalQueries.getPlayerById(userId).getName());
            
            for (String metric : metrics) {
                List<ValueTimeSeriesView> metricData = timeSeriesQueries.getHistoricalData(userId, metric, startTime, endTime);
                switch (metric) {
                    case "heart_rate":
                        historicalDataPlayer.setHeartRateData(metricData);
                        if (userId == playerId) {
                            averageHeartRate = timeSeriesQueries.getAverageValue(metricData);
                        }
                        break;
                    case "body_temperature":
                        historicalDataPlayer.setBodyTemperatureData(metricData);
                        if (userId == playerId) {
                            averageBodyTemperature = timeSeriesQueries.getAverageValue(metricData);
                        }
                        break;
                    case "respiratory_rate":
                        historicalDataPlayer.setRespiratoryRateData(metricData);
                        if (userId == playerId) {
                            averageRespiratoryRate = timeSeriesQueries.getAverageValue(metricData);
                        }
                        break;
                    default:
                        break;
                }

            }
            
            historicalDataPlayers.add(historicalDataPlayer);
        }

        
        return new HistoricalExtraDetailsResponse(sessionName, date, time, participants, historicalDataPlayers, averageHeartRate, averageBodyTemperature, averageRespiratoryRate);
    }
    
}