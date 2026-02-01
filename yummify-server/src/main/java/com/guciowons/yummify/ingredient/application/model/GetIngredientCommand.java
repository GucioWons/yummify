package com.guciowons.yummify.ingredient.application.model;

import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import com.guciowons.yummify.restaurant.RestaurantId;

public record GetIngredientCommand(IngredientId id, RestaurantId restaurantId) {
}
