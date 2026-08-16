package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class OrderIsEmptyException extends OrderDomainException {
    private final Order.Id id;

    public OrderIsEmptyException(Order.Id id) {
        super(OrderErrorMessage.ORDER_IS_EMPTY, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
