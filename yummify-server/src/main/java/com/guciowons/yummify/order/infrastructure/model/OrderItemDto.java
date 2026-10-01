package com.guciowons.yummify.order.infrastructure.model;

import com.guciowons.yummify.order.domain.entity.OrderItemStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDto(
        UUID id,
        UUID dishId,
        String name,
        BigDecimal price,
        int quantity,
        OrderItemStatus status
) {
}
