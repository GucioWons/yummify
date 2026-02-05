package com.guciowons.yummify.menu.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.List;

public record UpdateMenuSectionCommand(
        MenuSection.Id id,
        RestaurantId restaurantId,
        TranslatedString name,
        Integer position,
        List<MenuEntrySnapshot> entrySnapshots
) {
}
