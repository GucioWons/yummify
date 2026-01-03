package com.guciowons.yummify.menu.domain.repository;

import com.guciowons.yummify.common.core.domain.repository.RestaurantScopedRepository;
import com.guciowons.yummify.menu.domain.entity.Menu;

import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends RestaurantScopedRepository<Menu> {
    Optional<Menu> findByRestaurantIdAndActive(UUID restaurantId, boolean active);
}
