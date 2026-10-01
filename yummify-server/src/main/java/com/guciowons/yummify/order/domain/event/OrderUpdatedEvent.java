package com.guciowons.yummify.order.domain.event;

import com.guciowons.yummify.order.domain.entity.Order;

import java.util.UUID;

public record OrderUpdatedEvent(Order order, UUID userId) {
    public static OrderUpdatedEvent of(Order order, UUID userId) {
        return new OrderUpdatedEvent(order, userId);
    }
}
