package com.guciowons.yummify.menu.application.model;

import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;

import java.util.List;

public record UpdateMenuSectionEntriesCommand(
        MenuSection.Id id,
        MenuVersion.RestaurantId restaurantId,
        List<MenuEntrySnapshot> entrySnapshots
) {
}
