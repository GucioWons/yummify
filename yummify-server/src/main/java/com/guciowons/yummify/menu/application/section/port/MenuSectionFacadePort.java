package com.guciowons.yummify.menu.application.section.port;

import com.guciowons.yummify.menu.domain.entity.MenuSection;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MenuSectionFacadePort {
    MenuSection create(UUID restaurantId, Map<String, String> name);

    MenuSection updateName(UUID id, UUID restaurantId, Map<String, String> name);

    List<MenuSection> updatePosition(UUID id, UUID restaurantId, Integer position);
}
