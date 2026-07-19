package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

public record AddOrderItemCommand(
        Order.Id id,
        Order.RestaurantId restaurantId,
        OrderItem.DishId dishId,
        int quantity
) {
}
