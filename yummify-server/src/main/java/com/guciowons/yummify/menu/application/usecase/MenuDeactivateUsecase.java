package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import com.guciowons.yummify.menu.domain.service.MenuActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuDeactivateUsecase {
    private final MenuGetUsecase menuGetUsecase;
    private final MenuRepository menuRepository;
    private final MenuActivationService menuActivationService;

    public Menu deactivate(UUID id, UUID restaurantId) {
        Menu menu = menuGetUsecase.get(id, restaurantId);

        menuActivationService.deactivate(menu);

        return menuRepository.save(menu);
    }
}
