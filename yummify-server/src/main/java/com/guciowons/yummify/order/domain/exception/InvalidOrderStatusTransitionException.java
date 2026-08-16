package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class InvalidOrderStatusTransitionException extends OrderDomainException {
    private final OrderStatus from;
    private final OrderStatus to;

    public InvalidOrderStatusTransitionException(OrderStatus from, OrderStatus to) {
        super(
                OrderErrorMessage.INVALID_ORDER_STATUS_TRANSITION,
                ErrorProperty.of("from", from.name()),
                ErrorProperty.of("to", to.name())
        );
        this.from = from;
        this.to = to;
    }
}
