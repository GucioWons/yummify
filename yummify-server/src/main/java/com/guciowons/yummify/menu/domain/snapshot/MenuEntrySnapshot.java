package com.guciowons.yummify.menu.domain.snapshot;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;

public record MenuEntrySnapshot(MenuEntry.Id id, MenuEntry.DishId dishId, MenuEntry.Price price) {
}
