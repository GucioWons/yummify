package com.guciowons.yummify.menu.application.version.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.version.model.GetArchivedMenuVersionQuery;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetArchivedMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;

    public MenuVersion get(GetArchivedMenuVersionQuery query) {
        return menuVersionLookupService.getArchivedByIdAndRestaurantId(query.id(), query.restaurantId());
    }
}
