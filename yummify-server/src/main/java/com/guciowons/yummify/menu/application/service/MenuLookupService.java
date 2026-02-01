package com.guciowons.yummify.menu.application.service;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.value.MenuId;
import com.guciowons.yummify.menu.domain.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.domain.port.out.MenuRepositoryPort;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuLookupService {
    private final MenuRepositoryPort menuRepository;

    public Menu getByIdAndRestaurantId(MenuId id, RestaurantId restaurantId) throws MenuNotFoundException {
        return menuRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new MenuNotFoundException(id));
    }
}
