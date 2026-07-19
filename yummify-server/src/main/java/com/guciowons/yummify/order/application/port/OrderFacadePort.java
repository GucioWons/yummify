package com.guciowons.yummify.order.application.port;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

import java.util.UUID;

public interface OrderFacadePort {
    Order create(UUID restaurantId, UUID tableId);

    OrderItem addItem(
            UUID orderId,
            UUID restaurantId,
            UUID dishId,
            int quantity
    );
}
