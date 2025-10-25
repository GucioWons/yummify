package com.guciowons.yummify.menu.data;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.menu.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends RestaurantScopedRepository<Menu> {
    Optional<Menu> findByRestaurantIdAndActive(UUID restaurantId, boolean active);
}
