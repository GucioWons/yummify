package com.guciowons.yummify.menu.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;

public record CreateMenuSectionCommand(
        MenuVersion.RestaurantId restaurantId,
        TranslatedString name
) {
}
