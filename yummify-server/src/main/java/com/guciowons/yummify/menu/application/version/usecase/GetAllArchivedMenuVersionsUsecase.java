package com.guciowons.yummify.menu.application.version.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.version.model.GetMenuVersionQuery;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllArchivedMenuVersionsUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public List<MenuVersion> getAll(GetMenuVersionQuery query) {
        return menuVersionRepository.findAllArchivedByRestaurantId(query.restaurantId());
    }
}
