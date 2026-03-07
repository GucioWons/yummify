package com.guciowons.yummify.menu.application.section.model;

import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;

public record UpdateMenuSectionPositionCommand(
        MenuSection.Id id,
        MenuVersion.RestaurantId restaurantId,
        Integer position
) {
}
