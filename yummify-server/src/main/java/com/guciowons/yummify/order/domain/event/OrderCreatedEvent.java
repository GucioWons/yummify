package com.guciowons.yummify.order.domain.event;

import com.guciowons.yummify.order.domain.entity.Order;

public record OrderCreatedEvent(Order order) {
    public static OrderCreatedEvent of(Order order) {
        return new OrderCreatedEvent(order);
    }
}
