package com.guciowons.yummify.menu.application.entry.model;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;

public record CreateMenuEntryCommand(
        MenuSection.Id sectionId,
        MenuVersion.RestaurantId restaurantId,
        MenuEntry.DishId dishId,
        MenuEntry.Price price
) {
}
