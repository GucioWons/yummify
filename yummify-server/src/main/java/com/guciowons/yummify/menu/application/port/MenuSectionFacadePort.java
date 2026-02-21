package com.guciowons.yummify.menu.application.port;

import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MenuSectionFacadePort {
    MenuSection create(UUID restaurantId, Map<String, String> name);

    MenuSection updateEntries(UUID id, UUID restaurantId, List<MenuEntrySnapshot> entrySnapshots);

    MenuSection updateName(UUID id, UUID restaurantId, Map<String, String> name);

    List<MenuSection> updatePosition(UUID id, UUID restaurantId, Integer position);
}
