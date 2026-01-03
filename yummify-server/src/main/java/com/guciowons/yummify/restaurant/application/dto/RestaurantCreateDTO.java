package com.guciowons.yummify.restaurant.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @Valid @NotNull RestaurantManageDTO restaurant,
        @Valid @NotNull RestaurantOwnerDTO owner
) {
}
