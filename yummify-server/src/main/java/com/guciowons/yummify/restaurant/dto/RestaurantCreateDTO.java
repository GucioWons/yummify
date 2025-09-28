package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.auth.UserRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @Valid @NotNull RestaurantManageDTO restaurant,
        @Valid @NotNull UserRequestDTO owner
) {
}
