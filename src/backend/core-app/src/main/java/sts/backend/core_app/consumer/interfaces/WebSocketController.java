package sts.backend.core_app.consumer.interfaces;

import java.util.List;

import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;

public interface WebSocketController {
    public void sendRealTimeInfo(Long sessionId, RealTimeInfoResponse realTimeInfo);
    public void sendPlayersAvailableRealTimeInfo(Long teamId, List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo);
    public void sendPlayersRealTimeExtraDetails(Long playerId, RealTimeExtraDetailsResponse realTimeExtraDetailsResponse);
}
