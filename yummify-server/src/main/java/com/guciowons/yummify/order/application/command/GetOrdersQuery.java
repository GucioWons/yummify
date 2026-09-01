package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;

public record GetOrdersQuery(Order.RestaurantId restaurantId) {
}
