package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuGetUsecase {
    private final MenuRepository menuRepository;

    public Menu get(UUID id, UUID restaurantId) {
        return menuRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new MenuNotFoundException(id));
    }
}
