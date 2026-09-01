package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class PaymentIsNotRequestedException extends OrderDomainException {
    private final Order.Id id;

    public PaymentIsNotRequestedException(Order.Id id) {
        super(OrderErrorMessage.INVALID_ORDER_STATUS_TRANSITION, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
