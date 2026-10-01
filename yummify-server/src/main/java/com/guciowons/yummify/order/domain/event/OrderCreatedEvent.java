package com.guciowons.yummify.order.domain.event;

import com.guciowons.yummify.order.domain.entity.Order;

import java.util.UUID;

public record OrderCreatedEvent(Order order, UUID userId) {
    public static OrderCreatedEvent of(Order order, UUID userId) {
        return new OrderCreatedEvent(order, userId);
    }
}
