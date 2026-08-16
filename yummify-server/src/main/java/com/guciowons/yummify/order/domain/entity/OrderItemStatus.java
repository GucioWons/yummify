package com.guciowons.yummify.order.domain.entity;

import java.util.Set;


public enum OrderItemStatus {
    NEW(),
    IN_PROGRESS(NEW),
    READY(IN_PROGRESS),
    DELIVERED(READY),
    CANCELLED(NEW, IN_PROGRESS, READY);

    private final Set<OrderItemStatus> allowedTransitionsFrom;

    OrderItemStatus(OrderItemStatus... allowedTransitionsFrom) {
        this.allowedTransitionsFrom = Set.of(allowedTransitionsFrom);
    }

    public boolean canTransitionFrom(OrderItemStatus orderStatus) {
        return allowedTransitionsFrom.contains(orderStatus);
    }
}
