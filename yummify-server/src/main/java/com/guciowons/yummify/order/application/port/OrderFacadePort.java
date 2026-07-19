package com.guciowons.yummify.order.application.port;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface OrderFacadePort {
    Order create(UUID restaurantId, UUID tableId);

    OrderItem addItem(
            UUID orderId,
            UUID restaurantId,
            UUID dishId,
            Map<String, String> nameSnapshot,
            BigDecimal priceSnapshot,
            int quantity
    );
}
