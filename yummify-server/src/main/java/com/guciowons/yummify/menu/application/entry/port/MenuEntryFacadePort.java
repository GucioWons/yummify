package com.guciowons.yummify.menu.application.entry.port;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;

import java.math.BigDecimal;
import java.util.UUID;

public interface MenuEntryFacadePort {
    MenuEntry createEntry(UUID sectionId, UUID restaurantId, UUID dishId, BigDecimal price);

    MenuEntry updateEntry(UUID sectionId, UUID id, UUID restaurantId, UUID dishId, BigDecimal price);
}
