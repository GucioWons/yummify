package com.guciowons.yummify.menu.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.restaurant.RestaurantId;

public record CreateMenuSectionCommand(
        RestaurantId restaurantId,
        TranslatedString name,
        Integer position
) {
}
