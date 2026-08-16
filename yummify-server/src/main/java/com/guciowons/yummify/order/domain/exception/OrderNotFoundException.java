package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;

public class OrderNotFoundException extends OrderDomainException {
    private OrderNotFoundException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }

    public static OrderNotFoundException byId(Order.Id id) {
        return new OrderNotFoundException(OrderErrorMessage.ORDER_NOT_FOUND_BY_ID, ErrorProperty.of("id", id));
    }

    public static OrderNotFoundException byTableId(Order.TableId tableId) {
        return new OrderNotFoundException(OrderErrorMessage.ORDER_NOT_FOUND_BY_ID, ErrorProperty.of("tableId", tableId));
    }
}
