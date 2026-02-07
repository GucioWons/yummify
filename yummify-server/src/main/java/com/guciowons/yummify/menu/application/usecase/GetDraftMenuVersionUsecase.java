package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.GetDraftMenuVersionCommand;
import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetDraftMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;

    public MenuVersion get(GetDraftMenuVersionCommand command) {
        return menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());
    }
}
