package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.domain.entity.Dish;

import java.util.List;
import java.util.UUID;

public record CreateDishCommand(
        Dish.RestaurantId restaurantId,
        TranslatedString name,
        TranslatedString description,
        List<UUID> ingredientIds
) {
}
