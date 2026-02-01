package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @Valid @NotNull RestaurantManageDTO restaurant,
        @Valid @NotNull RestaurantOwnerDTO owner
) {
}
