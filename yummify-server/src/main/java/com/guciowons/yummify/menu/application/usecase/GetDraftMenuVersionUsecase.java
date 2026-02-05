package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.GetDraftMenuVersionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetDraftMenuVersionUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion get(GetDraftMenuVersionCommand command) {
        return menuVersionRepository.findDraftByRestaurantId(command.restaurantId())
                .orElseThrow();
    }
}
