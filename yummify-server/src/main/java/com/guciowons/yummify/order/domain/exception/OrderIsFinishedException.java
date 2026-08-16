package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class OrderIsFinishedException extends OrderDomainException {
    private final Order.Id id;

    public OrderIsFinishedException(Order.Id id) {
        super(OrderErrorMessage.ORDER_IS_FINISHED, ErrorProperty.of("id", id));
        this.id = id;
    }
}
