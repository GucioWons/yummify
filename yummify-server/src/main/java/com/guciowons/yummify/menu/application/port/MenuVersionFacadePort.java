package com.guciowons.yummify.menu.application.port;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;

import java.util.List;
import java.util.UUID;

public interface MenuVersionFacadePort {
    MenuVersion create(UUID restaurantId);

    List<MenuVersion> getAllArchived(UUID restaurantId);

    MenuVersion getDraft(UUID restaurantId);

    MenuVersion getPublished(UUID restaurantId);

    MenuVersion getArchived(UUID id, UUID restaurantId);

    MenuVersion publish(UUID restaurantId);

    MenuVersion restore(UUID id, UUID restaurantId);
}
