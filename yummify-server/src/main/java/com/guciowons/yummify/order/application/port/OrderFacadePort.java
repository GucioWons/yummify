package com.guciowons.yummify.order.application.port;

import com.guciowons.yummify.order.domain.entity.Order;

import java.util.UUID;

public interface OrderFacadePort {
    Order create(UUID restaurantId, UUID tableId);
}
