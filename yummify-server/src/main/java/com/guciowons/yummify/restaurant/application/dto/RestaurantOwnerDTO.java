package com.guciowons.yummify.restaurant.application.dto;

import jakarta.validation.constraints.NotNull;

public record RestaurantOwnerDTO(
        @NotNull String email,
        @NotNull String username,
        @NotNull String firstName,
        @NotNull String lastName
) {
}
