package com.guciowons.yummify.order.domain.event;

import com.guciowons.yummify.order.domain.entity.Order;

public record OrderUpdatedEvent(Order order) {
    public static OrderUpdatedEvent of(Order order) {
        return new OrderUpdatedEvent(order);
    }
}
