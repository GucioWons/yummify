package com.guciowons.yummify.restaurant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

public record RestaurantDTO(@Null UUID id, @NotNull String name, @NotNull String description) {
}
