package com.guciowons.yummify.order.domain.entity;

import java.util.Set;

public enum OrderStatus {
    NEW(),
    SUBMITTED(NEW),
    IN_PREPARATION(SUBMITTED),
    DELIVERED(IN_PREPARATION),
    COMPLETED(DELIVERED),
    CANCELLED(NEW, SUBMITTED, DELIVERED);

    private final Set<OrderStatus> allowedTransitionsFrom;

    OrderStatus(OrderStatus... allowedTransitionsFrom) {
        this.allowedTransitionsFrom = Set.of(allowedTransitionsFrom);
    }

    public boolean canTransitionFrom(OrderStatus orderStatus) {
        return allowedTransitionsFrom.contains(orderStatus);
    }
}
