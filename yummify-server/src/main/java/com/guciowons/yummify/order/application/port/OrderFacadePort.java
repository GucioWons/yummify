package com.guciowons.yummify.order.application.port;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderFacadePort {
    Order create(UUID userId, UUID tableId);

    OrderItem addItem(
            UUID userId,
            UUID restaurantId,
            UUID dishId,
            int quantity
    );

    void removeItem(UUID userId, UUID restaurantId, UUID orderItemId);

    Order submit(UUID userId, UUID restaurantId);

    Order cancel(UUID userId, UUID uuid);

    OrderItem startPreparation(UUID orderId, UUID restaurantId, UUID itemId);

    OrderItem finishPreparation(UUID orderId, UUID restaurantId, UUID itemId);

    OrderItem serve(UUID orderId, UUID restaurantId, UUID itemId);

    Order requestAssistance(UUID userId, UUID restaurantId);

    Order requestPayment(UUID userId, UUID restaurantId);

    Order complete(UUID id, UUID restaurantId);

    List<Order> getCurrent(UUID restaurantId);

    List<Order> getOld(UUID restaurantId);
}
