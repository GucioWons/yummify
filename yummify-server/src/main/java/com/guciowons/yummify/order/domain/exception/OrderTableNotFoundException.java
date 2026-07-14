package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import lombok.Getter;

@Getter
public class OrderTableNotFoundException extends OrderDomainException {
    private final Order.TableId tableId;

    public OrderTableNotFoundException(Order.TableId tableId) {
        super(OrderErrorMessage.ORDER_TABLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", tableId));
        this.tableId = tableId;
    }
}
