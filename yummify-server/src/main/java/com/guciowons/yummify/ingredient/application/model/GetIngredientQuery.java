package com.guciowons.yummify.ingredient.application.model;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;

public record GetIngredientQuery(Ingredient.Id id, Ingredient.RestaurantId restaurantId) {
}
