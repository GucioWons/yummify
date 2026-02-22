package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import jakarta.validation.constraints.NotNull;

public record RestaurantOwnerDto(
        @NotNull String email,
        @NotNull String username,
        @NotNull String firstName,
        @NotNull String lastName
) {
}
