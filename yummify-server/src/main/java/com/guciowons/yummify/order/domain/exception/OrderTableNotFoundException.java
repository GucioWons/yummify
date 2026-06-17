package com.guciowons.yummify.order.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.order.domain.entity.Order;
import lombok.Getter;

@Getter
public class OrderTableNotFoundException extends DomainException {
    private final Order.TableId tableId;

    public OrderTableNotFoundException(Order.TableId tableId) {
        super("Order table not found");
        this.tableId = tableId;
    }
}
