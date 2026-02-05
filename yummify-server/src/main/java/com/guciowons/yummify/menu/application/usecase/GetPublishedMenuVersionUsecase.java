package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.GetPublishedMenuVersionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetPublishedMenuVersionUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion get(GetPublishedMenuVersionCommand command) {
        return menuVersionRepository.findPublishedByRestaurantId(command.restaurantId())
                .orElseThrow();
    }
}
