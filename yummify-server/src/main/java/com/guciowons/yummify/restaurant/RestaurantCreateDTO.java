package com.guciowons.yummify.restaurant;

import com.guciowons.yummify.auth.UserRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @NotNull String name,
        @NotNull String description,
        @Valid @NotNull UserRequestDTO owner
) {
}
