package com.guciowons.yummify.order.infrastructure.out.ws.message;

import com.guciowons.yummify.order.infrastructure.model.OrderClientDto;

import java.util.UUID;

public record OrderWebSocketMessage(Type type, UUID userId, OrderClientDto order) {
    public enum Type {
        CREATED,
        UPDATED
    }
}
