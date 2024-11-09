package sts.backend.core_app.services.analysis;

import java.sql.Time;
import java.time.Duration;
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
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.HistoricalAnalysis;
import sts.backend.core_app.models.Match;
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
        // Get the session information
        Session session = relationalQueries.getSessionById(sessionId);

        String sessionName = session.getName();
        LocalDateTime startTime = session.getStartTime();
        LocalDateTime endTime = session.getEndTime();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startDate = startTime.format(dateFormatter);
        String endTimeFormatted = endTime.format(timeFormatter);
        String date = startDate + "-" + endTimeFormatted; // "Oct 12, 2021 12:00-13:30"
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

        if (session instanceof Match) {
            return new HistoricalExtraDetailsResponse(sessionName, date, time, participants, historicalDataPlayers, averageHeartRate, averageBodyTemperature, averageRespiratoryRate, opponentTeam, matchType, location, weather);
        } 
        return new HistoricalExtraDetailsResponse(sessionName, date, time, participants, historicalDataPlayers, averageHeartRate, averageBodyTemperature, averageRespiratoryRate);
    }

    
}