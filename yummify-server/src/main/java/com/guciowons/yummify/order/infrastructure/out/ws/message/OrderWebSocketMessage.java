package com.guciowons.yummify.order.infrastructure.out.ws.message;

import com.guciowons.yummify.order.infrastructure.model.OrderDto;

import java.util.UUID;

public record OrderWebSocketMessage(Type type, UUID userId, OrderDto order) {
    public enum Type {
        CREATED,
        UPDATED
    }
}
