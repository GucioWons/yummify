package com.guciowons.yummify.menu.domain.port.out;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;

import java.util.List;
import java.util.Optional;

public interface MenuVersionRepository {
    void save(MenuVersion menuVersion);

    Optional<MenuVersion> findArchivedByIdAndRestaurantId(MenuVersion.Id id, MenuVersion.RestaurantId restaurantId);

    Optional<MenuVersion> findDraftByRestaurantId(MenuVersion.RestaurantId restaurantId);

    Optional<MenuVersion> findPublishedByRestaurantId(MenuVersion.RestaurantId restaurantId);

    List<MenuVersion> findAllArchivedByRestaurantId(MenuVersion.RestaurantId restaurantId);

    boolean existsByRestaurantId(MenuVersion.RestaurantId restaurantId);
}
