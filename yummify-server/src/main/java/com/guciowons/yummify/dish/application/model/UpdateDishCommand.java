package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.List;
import java.util.UUID;

public record UpdateDishCommand(
        DishId id,
        RestaurantId restaurantId,
        TranslatedString name,
        TranslatedString description,
        List<UUID> ingredientIds
) {
}
