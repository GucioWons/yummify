package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class OrderItemNotFoundException extends OrderDomainException {
    private final OrderItem.Id orderItemId;

    public OrderItemNotFoundException(OrderItem.Id orderItemId) {
        super(OrderErrorMessage.ORDER_TABLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", orderItemId.value()));
        this.orderItemId = orderItemId;
    }
}
