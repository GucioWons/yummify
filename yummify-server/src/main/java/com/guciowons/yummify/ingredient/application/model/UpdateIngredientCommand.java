package com.guciowons.yummify.ingredient.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;

public record UpdateIngredientCommand(Ingredient.Id id, Ingredient.RestaurantId restaurantId, TranslatedString name) {
}
