package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;

import java.util.UUID;

public record CreateOrderCommand(UUID userId, Order.RestaurantId restaurantId) {
}
