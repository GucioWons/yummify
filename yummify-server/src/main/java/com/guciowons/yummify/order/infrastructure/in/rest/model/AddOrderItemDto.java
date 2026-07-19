package com.guciowons.yummify.order.infrastructure.in.rest.model;

import java.util.UUID;

public record AddOrderItemDto(UUID dishId, int quantity) {
}
