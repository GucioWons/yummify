package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

import java.util.UUID;

public record ServeOrderItemCommand(Order.Id id, UUID userId, Order.RestaurantId restaurantId, OrderItem.Id itemId) {
}
