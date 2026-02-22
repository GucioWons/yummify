package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDto(
        @Valid @NotNull RestaurantManageDto restaurant,
        @Valid @NotNull RestaurantOwnerDto owner
) {
}
