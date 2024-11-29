package sts.backend.core_app.consumer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendRealTimeInfo(RealTimeInfoResponse realTimeInfo) {
        messagingTemplate.convertAndSend("/topic/realTimeInfo", realTimeInfo);
    }
}
