package sts.backend.core_app.services.analysis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.session.NotificationResponse;
import sts.backend.core_app.dto.session.SessionLastMetricValues;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;

@Service
public class RealTimeAnalysisImpl implements RealTimeAnalysis {
    
    private final TimeSeriesQueries timeSeriesQueries;
    private final RelationalQueries relationalQueries;

    public RealTimeAnalysisImpl(TimeSeriesQueries timeSeriesQueries, RelationalQueries relationalQueries) {
        this.timeSeriesQueries = timeSeriesQueries;
        this.relationalQueries = relationalQueries;
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }

    @Override
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        return timeSeriesQueries.getRealTimeExtraDetailsLast24Hours(playerId);
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

}