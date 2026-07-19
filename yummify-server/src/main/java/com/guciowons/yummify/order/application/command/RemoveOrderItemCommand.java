package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

public record RemoveOrderItemCommand(Order.Id orderId, Order.RestaurantId restaurantId, OrderItem.Id itemId) {
}
