package sts.backend.core_app.services.analysis;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.dto.session.NotificationResponse;
import sts.backend.core_app.dto.session.SessionLastMetricValues;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;

@Service
public class RealTimeAnalysisImpl implements RealTimeAnalysis {
    
    private final TimeSeriesQueries timeSeriesQueries;
    private final RelationalQueries relationalQueries;

    public RealTimeAnalysisImpl(RelationalQueries relationalQueries, TimeSeriesQueries timeSeriesQueries) {
        this.relationalQueries = relationalQueries;
        this.timeSeriesQueries = timeSeriesQueries;
    }

    @Override
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        return timeSeriesQueries.getRealTimeExtraDetailsLast24Hours(playerId);
    }

    public List<PlayersAvailableRealTimeInfo> getPlayersAvailableRealTimeInfo(Long teamId) throws ResourceNotFoundException {
        List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo = new ArrayList<>();
        List<Player> players = relationalQueries.getAvailablePlayersByTeamId(teamId);
        LocalDateTime initialTimestamp = LocalDateTime.now().minusMinutes(5);

        for (Player player : players) {
            List<ValueTimeSeriesView> heartRateData = timeSeriesQueries.getHeartRateData(player.getUserId(), initialTimestamp);
            Double currentHeartRate = null;
            if (heartRateData.size() > 0) {
                heartRateData.get(heartRateData.size() - 1).getValue();
            }

            playersAvailableRealTimeInfo.add(new PlayersAvailableRealTimeInfo(player.getName(), player.getUserId(), player.getProfilePictureUrl(), heartRateData, currentHeartRate));
        }
        
        return playersAvailableRealTimeInfo;
    }

    public Set<NotificationResponse> getNotifications(Long sessionId) throws ResourceNotFoundException {
        Set<NotificationResponse> notifications = new HashSet<>();

        Set<Player> players = relationalQueries.getPlayersInSessionBySessionId(sessionId);

        for (Player player : players) {
            SessionLastMetricValues sessionLastMetricValues = timeSeriesQueries.getLastMetricValuesByPlayerId(player.getUserId());
            Double heartRate = sessionLastMetricValues.getHeartRate();
            Double bodyTemperature = sessionLastMetricValues.getBodyTemperature();
            Double respiratoryRate = sessionLastMetricValues.getRespiratoryRate();
            if (heartRate > 180) {
                notifications.add(new NotificationResponse(player.getName(), "Incident - Hight Heart Rate (" + heartRate + " bpm)"));
            }
            if (bodyTemperature > 38) {
                notifications.add(new NotificationResponse(player.getName(), "Incident - Hight Body Temperature (" + bodyTemperature + " ºC)"));
            }
            if (respiratoryRate > 30) {
                notifications.add(new NotificationResponse(player.getName(), "Incident - Hight Respiratory Rate (" + respiratoryRate + " rpm)"));
            }
        }

        return notifications;
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
            return new RealTimeExtraDetailsResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers, lastHeartRate, lastBodyTemperature, lastRespiratoryRate, opponentTeam, matchType, location, weather);
        } 
        return new RealTimeExtraDetailsResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers, lastHeartRate, lastBodyTemperature, lastRespiratoryRate);
    }

    public RealTimeInfoResponse getRealTimeInfo(Long sessionId) throws ResourceNotFoundException {
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

        for (Long userId : playerIds) {
            RealTimeExtraDetailsPlayer historicalDataPlayer = new RealTimeExtraDetailsPlayer();
            
            historicalDataPlayer.setPlayerId(userId);
            historicalDataPlayer.setPlayerName(relationalQueries.getPlayerById(userId).getName());
            
            for (String metric : metrics) {
                List<ValueTimeSeriesView> metricData = timeSeriesQueries.getHistoricalData(userId, metric, startTime, endTime);
                switch (metric) {
                    case "heart_rate":
                        historicalDataPlayer.setHeartRateData(metricData);
                        break;
                    case "body_temperature":
                        historicalDataPlayer.setBodyTemperatureData(metricData);
                        break;
                    case "respiratory_rate":
                        historicalDataPlayer.setRespiratoryRateData(metricData);
                        break;
                    default:
                        break;
                }

            }
            
            historicalDataPlayers.add(historicalDataPlayer);
        }

        if (session instanceof Match) {
            return new RealTimeInfoResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers, opponentTeam, matchType, location, weather);
        } 
        return new RealTimeInfoResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers);
    }


    public RealTimeInfoResponse getRealTimeInfoTrainer(Long trainerId) throws ResourceNotFoundException {
        // Get the session information
        Session session = relationalQueries.getSessionByTrainerId(trainerId);
        Long sessionId = session.getSessionId();

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
        List<Long> playerIds = relationalQueries.getPlayerIdsBySessionId(session.getSessionId());
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

        for (Long userId : playerIds) {
            RealTimeExtraDetailsPlayer historicalDataPlayer = new RealTimeExtraDetailsPlayer();
            
            historicalDataPlayer.setPlayerId(userId);
            historicalDataPlayer.setPlayerName(relationalQueries.getPlayerById(userId).getName());
            
            for (String metric : metrics) {
                List<ValueTimeSeriesView> metricData = timeSeriesQueries.getHistoricalData(userId, metric, startTime, endTime);
                switch (metric) {
                    case "heart_rate":
                        historicalDataPlayer.setHeartRateData(metricData);
                        break;
                    case "body_temperature":
                        historicalDataPlayer.setBodyTemperatureData(metricData);
                        break;
                    case "respiratory_rate":
                        historicalDataPlayer.setRespiratoryRateData(metricData);
                        break;
                    default:
                        break;
                }

            }
            
            historicalDataPlayers.add(historicalDataPlayer);
        }

        if (session instanceof Match) {
            return new RealTimeInfoResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers, opponentTeam, matchType, location, weather);
        } 
        return new RealTimeInfoResponse(sessionId, sessionName, date, time, participants, historicalDataPlayers);
    }
}