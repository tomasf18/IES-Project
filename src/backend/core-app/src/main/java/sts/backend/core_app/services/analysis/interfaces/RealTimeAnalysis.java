package sts.backend.core_app.services.analysis.interfaces;

import java.util.Set;


import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.dto.session.NotificationResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface RealTimeAnalysis {
    
    // --- Create methods ---
    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException;

    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);

    public Set<PlayersAvailableRealTimeInfo> getPlayersAvailableRealTimeInfo(Long teamId) throws ResourceNotFoundException;

    public Set<NotificationResponse> getNotifications(Long sessionId) throws ResourceNotFoundException;

}
