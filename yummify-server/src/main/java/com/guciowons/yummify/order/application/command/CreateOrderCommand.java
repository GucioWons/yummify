package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;

public record CreateOrderCommand(Order.RestaurantId restaurantId, Order.TableId tableId) {
}
