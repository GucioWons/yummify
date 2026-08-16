package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.OrderItemStatus;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class InvalidOrderItemStatusTransitionException extends OrderDomainException {
    private final OrderItemStatus from;
    private final OrderItemStatus to;

    public InvalidOrderItemStatusTransitionException(OrderItemStatus from, OrderItemStatus to) {
        super(
                OrderErrorMessage.INVALID_ORDER_STATUS_TRANSITION,
                ErrorProperty.of("from", from.name()),
                ErrorProperty.of("to", to.name())
        );
        this.from = from;
        this.to = to;
    }
}
