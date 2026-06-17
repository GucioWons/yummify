package com.guciowons.yummify.order.infrastructure.in.rest.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemClientDto(
        UUID id,
        UUID dishId,
        String name,
        BigDecimal price
) {
}
