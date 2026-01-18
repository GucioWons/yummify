package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.dish.domain.entity.value.DishIngredientIds;
import com.guciowons.yummify.restaurant.RestaurantId;

public record UpdateDishCommand(
        DishId id,
        RestaurantId restaurantId,
        TranslatedString name,
        TranslatedString description,
        DishIngredientIds ingredientIds
) {
}
