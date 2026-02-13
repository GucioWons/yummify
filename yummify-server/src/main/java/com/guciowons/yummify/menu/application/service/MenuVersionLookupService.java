package com.guciowons.yummify.menu.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.exception.ArchivedMenuNotFoundException;
import com.guciowons.yummify.menu.domain.exception.DraftMenuVersionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.PublishedMenuVersionNotFoundException;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class MenuVersionLookupService {
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion getDraftByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return menuVersionRepository.findDraftByRestaurantId(restaurantId)
                .orElseThrow(DraftMenuVersionNotFoundException::new);
    }

    public MenuVersion getPublishedByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return menuVersionRepository.findPublishedByRestaurantId(restaurantId)
                .orElseThrow(PublishedMenuVersionNotFoundException::new);
    }

    public MenuVersion getArchivedByIdAndRestaurantId(MenuVersion.Id id, MenuVersion.RestaurantId restaurantId) {
        return menuVersionRepository.findArchivedByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new ArchivedMenuNotFoundException(id));
    }
}
