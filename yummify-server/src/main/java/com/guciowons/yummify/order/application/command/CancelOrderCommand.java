package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;

public record CancelOrderCommand(Order.Id id, Order.RestaurantId restaurantId) {
}
