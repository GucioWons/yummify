package com.guciowons.yummify.order.infrastructure.model;

import java.util.UUID;

public record AddOrderItemDto(UUID dishId, int quantity) {
}
