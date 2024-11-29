package sts.backend.core_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${FRONTEND_IP}")
    private String frontendIp;

    @Value("${FRONTEND_LOCAL_PORT}")
    private String frontendPort;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enables simple broker for topics
        config.enableSimpleBroker("/topic"); // Clients can subscribe to "/topic/{sensorId}"
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Clients connect to WebSocket at "/backend-ws"
        registry.addEndpoint("/backend-ws").setAllowedOrigins("http://" + frontendIp + ":" + frontendPort);
    }
}
