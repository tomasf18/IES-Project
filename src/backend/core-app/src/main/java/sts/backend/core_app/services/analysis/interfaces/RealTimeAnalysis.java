package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;
import java.util.Set;


import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.dto.session.NotificationResponse;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface RealTimeAnalysis {
    
    // --- Create methods ---
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);

    public List<PlayersAvailableRealTimeInfo> getPlayersAvailableRealTimeInfo(Long teamId) throws ResourceNotFoundException;

    public Set<NotificationResponse> getNotifications(Long sessionId) throws ResourceNotFoundException;

    public RealTimeInfoResponse getRealTimeInfo(Long sessionId) throws ResourceNotFoundException; 

    public RealTimeInfoResponse getRealTimeInfoTrainer(Long trainerId) throws ResourceNotFoundException;

    public Long getPlayerIdBySensorId(Long sensorId);

    public RealTimeExtraDetailsResponse getRealTimeExtraDetails(Long sessionId, Long playerId) throws ResourceNotFoundException;
}
