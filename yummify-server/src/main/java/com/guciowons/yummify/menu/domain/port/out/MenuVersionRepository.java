package com.guciowons.yummify.menu.domain.port.out;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface MenuVersionRepository {
    void save(MenuVersion menuVersion);

    Optional<MenuVersion> findDraftByRestaurantId(RestaurantId restaurantId);

    Optional<MenuVersion> findPublishedByRestaurantId(RestaurantId restaurantId);

    List<MenuVersion> findAllByRestaurantId(RestaurantId restaurantId);

    boolean existsByRestaurantId(RestaurantId restaurantId);
}
