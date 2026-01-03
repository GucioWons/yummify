package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import com.guciowons.yummify.menu.domain.service.MenuActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuActivateUsecase {
    private final MenuGetUsecase menuGetUsecase;
    private final MenuRepository menuRepository;
    private final MenuActivationService menuActivationService;

    public Menu activate(UUID id, UUID restaurantId) {
        Menu menu = menuGetUsecase.get(id, restaurantId);

        menuActivationService.activate(menu);

        return menuRepository.save(menu);
    }
}
