package com.guciowons.yummify.menu.application.version.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.version.model.GetMenuVersionQuery;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetPublishedMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;

    public MenuVersion get(GetMenuVersionQuery query) {
        return menuVersionLookupService.getPublishedByRestaurantId(query.restaurantId());
    }
}
