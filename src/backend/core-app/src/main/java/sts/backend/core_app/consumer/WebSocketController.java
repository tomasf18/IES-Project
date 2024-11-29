package sts.backend.core_app.consumer;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendRealTimeInfo(RealTimeInfoResponse realTimeInfo) {
        messagingTemplate.convertAndSend("/topic/realTimeInfo", realTimeInfo);
    }

    public void sendPlayersAvailableRealTimeInfo(List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo) {
        messagingTemplate.convertAndSend("/topic/playersAvailableRealTimeInfo", playersAvailableRealTimeInfo);
    }
}
