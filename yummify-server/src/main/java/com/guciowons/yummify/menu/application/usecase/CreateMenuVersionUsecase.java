package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.CreateMenuVersionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateMenuVersionUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion create(CreateMenuVersionCommand command) {
        if (menuVersionRepository.existsByRestaurantId(command.restaurantId())) {
            throw new RuntimeException();
        }

        MenuVersion menu = MenuVersion.create(command.restaurantId());
        menuVersionRepository.save(menu);
        return menu;
    }
}
