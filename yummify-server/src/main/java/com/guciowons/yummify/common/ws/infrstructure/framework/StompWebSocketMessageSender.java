package com.guciowons.yummify.common.ws.infrstructure.framework;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.UUID;

@RequiredArgsConstructor
public class StompWebSocketMessageSender {
    private final SimpMessagingTemplate messagingTemplate;
    private final WebSocketSessionRegistry sessionRegistry;

    public void sendToRestaurant(UUID restaurantId, String destination, Object message) {
        sessionRegistry.getSessions(restaurantId)
                .forEach(sessionId -> messagingTemplate.convertAndSendToUser(sessionId, destination, message));
    }
}
