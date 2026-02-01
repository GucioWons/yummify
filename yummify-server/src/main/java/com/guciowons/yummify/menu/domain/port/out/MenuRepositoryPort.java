package com.guciowons.yummify.menu.domain.port.out;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.value.MenuId;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.Optional;

public interface MenuRepositoryPort {
    Menu save(Menu menu);

    Optional<Menu> findByIdAndRestaurantId(MenuId id, RestaurantId restaurantId);
}
