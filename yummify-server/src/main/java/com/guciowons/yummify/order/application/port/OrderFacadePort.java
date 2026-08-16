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

    void removeItem(UUID orderId, UUID restaurantId, UUID orderItemId);

    Order submit(UUID orderId, UUID restaurantId);

    Order cancel(UUID id, UUID uuid);

    OrderItem startPreparation(UUID orderId, UUID restaurantId, UUID itemId);

    OrderItem finishPreparation(UUID orderId, UUID restaurantId, UUID itemId);

    OrderItem serve(UUID orderId, UUID restaurantId, UUID itemId);
}
