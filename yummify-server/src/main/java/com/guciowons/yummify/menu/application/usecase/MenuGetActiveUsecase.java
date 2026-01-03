package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.exception.NoActiveMenuException;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuGetActiveUsecase {
    private final MenuRepository menuRepository;

    public Menu get(UUID restaurantId) {
        return menuRepository.findByRestaurantIdAndActive(restaurantId, true)
                .orElseThrow(NoActiveMenuException::new);
    }
}
