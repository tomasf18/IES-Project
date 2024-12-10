package sts.backend.core_app.consumer.interfaces;

import java.util.List;
import java.util.Map;

import sts.backend.core_app.dto.admin.SensorsLast5Days;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;

public interface WebSocketController {
    public void sendRealTimeInfo(Long sessionId, RealTimeInfoResponse realTimeInfo);
    public void sendPlayersAvailableRealTimeInfo(Long teamId, List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo);
    public void sendPlayersRealTimeExtraDetails(Long playerId, RealTimeExtraDetailsResponse realTimeExtraDetailsResponse);
    public void sendSensorsTeamWeek(List<SensorsTeamWeek> sensorsTeamWeek);
    public void sendSensorsLast5Days(List<SensorsLast5Days> sensorsLast5Days);
    public void sendSensorsDay(Map<String, Integer> sensorsDay);
}
