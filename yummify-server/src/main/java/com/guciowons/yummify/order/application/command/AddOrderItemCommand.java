package com.guciowons.yummify.order.application.command;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;

import java.math.BigDecimal;

public record AddOrderItemCommand(
        Order.Id id,
        Order.RestaurantId restaurantId,
        OrderItem.DishId dishId,
        TranslatedString nameSnapshot,
        BigDecimal priceSnapshot,
        int quantity
) {
}
