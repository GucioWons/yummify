package com.guciowons.yummify.order.infrastructure.model;

import com.guciowons.yummify.order.domain.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID tableId,
        List<OrderItemDto> items,
        OrderStatus status,
        boolean assistanceRequested,
        boolean paymentRequested
) {
}
