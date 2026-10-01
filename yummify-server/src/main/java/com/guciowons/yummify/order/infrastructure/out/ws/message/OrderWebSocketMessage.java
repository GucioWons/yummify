package com.guciowons.yummify.order.infrastructure.out.ws.message;

import com.guciowons.yummify.order.infrastructure.model.OrderClientDto;

public record OrderWebSocketMessage(Type type, OrderClientDto order) {
    public enum Type {
        CREATED,
        UPDATED
    }
}
