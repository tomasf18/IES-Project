package sts.backend.core_app.consumer;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.consumer.interfaces.WebSocketController;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.dto.admin.SensorsLast5Days;

@Controller
public class WebSocketControllerImp implements WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketControllerImp(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendRealTimeInfo(Long sessionId, RealTimeInfoResponse realTimeInfo) {
        messagingTemplate.convertAndSend("/topic/realTimeInfo/" + sessionId, realTimeInfo);
    }

    public void sendPlayersAvailableRealTimeInfo(Long teamId, List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo) {
        messagingTemplate.convertAndSend("/topic/playersAvailableRealTimeInfo/" + teamId, playersAvailableRealTimeInfo);
    }

    public void sendPlayersRealTimeExtraDetails(Long playerId, RealTimeExtraDetailsResponse realTimeExtraDetailsResponse) {
        messagingTemplate.convertAndSend("/topic/realTimeExtraDetails/" + playerId, realTimeExtraDetailsResponse);
    }

    public void sendSensorsTeamWeek(List<SensorsTeamWeek> sensorsTeamWeek) {
        messagingTemplate.convertAndSend("/topic/sensorsTeamWeek", sensorsTeamWeek);
    }

    public void sendSensorsLast5Days(List<SensorsLast5Days> sensorsLast5Days) {
        messagingTemplate.convertAndSend("/topic/sensorsLast5Days", sensorsLast5Days);
    }
}
