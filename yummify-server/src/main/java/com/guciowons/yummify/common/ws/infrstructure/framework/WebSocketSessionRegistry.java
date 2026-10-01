package com.guciowons.yummify.common.ws.infrstructure.framework;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionRegistry {
    private final Map<UUID, Set<String>> sessions = new ConcurrentHashMap<>();

    public void register(UUID restaurantId, String sessionId) {
        sessions.computeIfAbsent(restaurantId, ignored -> ConcurrentHashMap.newKeySet())
                .add(sessionId);
    }

    public void unregister(UUID restaurantId, String sessionId) {
        Set<String> restaurantSessions = sessions.get(restaurantId);

        if (restaurantSessions == null) {
            return;
        }

        restaurantSessions.remove(sessionId);

        if (restaurantSessions.isEmpty()) {
            sessions.remove(restaurantId);
        }
    }

    public Set<String> getSessions(UUID restaurantId) {
        return sessions.getOrDefault(restaurantId, Set.of());
    }
}
