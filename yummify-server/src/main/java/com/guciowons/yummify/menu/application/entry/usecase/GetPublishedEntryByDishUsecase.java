package com.guciowons.yummify.menu.application.entry.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.entry.model.GetPublishedEntryByDishQuery;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetPublishedEntryByDishUsecase {
    private final MenuVersionLookupService menuVersionLookupService;

    public MenuEntry getByDish(GetPublishedEntryByDishQuery query) {
        return menuVersionLookupService.getPublishedByRestaurantId(query.restaurantId()).getSections().stream()
                .flatMap(section -> section.getEntries().stream())
                .filter(entry -> entry.getDishId().equals(query.dishId()))
                .findFirst()
                .orElseThrow();
    }
}
