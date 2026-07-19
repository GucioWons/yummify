package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class OrderNotFoundException extends OrderDomainException {
    private final Order.Id id;

    public OrderNotFoundException(Order.Id id) {
        super(OrderErrorMessage.ORDER_TABLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", id));
        this.id = id;
    }
}
