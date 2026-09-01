package com.guciowons.yummify.order.domain.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
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

    public static final List<OrderStatus> CURRENT_STATUSES = Arrays.stream(values())
            .filter(status -> !status.isFinished())
            .toList();

    public static final List<OrderStatus> OLD_STATUSES = Arrays.stream(values())
            .filter(OrderStatus::isFinished)
            .toList();
}
