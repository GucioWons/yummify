package com.guciowons.yummify.ingredient.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.restaurant.RestaurantId;

public record CreateIngredientCommand(RestaurantId restaurantId, TranslatedString name) {
}
