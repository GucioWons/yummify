package com.guciowons.yummify.order.infrastructure.in.rest.model;

import com.guciowons.yummify.order.domain.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderClientDto(
        UUID id,
        UUID tableId,
        List<OrderItemClientDto> items,
        OrderStatus status
) {
}
