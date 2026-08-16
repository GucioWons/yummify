package com.guciowons.yummify.order.domain.entity;

import lombok.Getter;

import java.util.Set;

public enum OrderStatus {
    NEW(false),
    SUBMITTED(false, NEW),
    IN_PREPARATION(false, SUBMITTED),
    DELIVERED(false, IN_PREPARATION),
    COMPLETED(true, DELIVERED),
    CANCELLED(true, NEW, SUBMITTED, DELIVERED);

    @Getter
    private final boolean finished;
    private final Set<OrderStatus> allowedTransitionsFrom;

    OrderStatus(boolean finished, OrderStatus... allowedTransitionsFrom) {
        this.finished = finished;
        this.allowedTransitionsFrom = Set.of(allowedTransitionsFrom);
    }

    public boolean canTransitionFrom(OrderStatus orderStatus) {
        return allowedTransitionsFrom.contains(orderStatus);
    }
}
