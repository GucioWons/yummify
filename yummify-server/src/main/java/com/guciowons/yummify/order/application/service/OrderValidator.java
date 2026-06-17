package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import com.guciowons.yummify.table.TableExistencePort;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class OrderValidator {
    private final TableExistencePort tableExistencePort;

    public void validate(Order.TableId tableId, Order.RestaurantId restaurantId) {
        if (!tableExistencePort.exists(tableId.value(), restaurantId.value())) {
            throw new OrderTableNotFoundException(tableId);
        }
    }

}
