package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.GetAllMenuVersionsCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllMenuVersionsUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public List<MenuVersion> getAll(GetAllMenuVersionsCommand command) {
        return menuVersionRepository.findAllByRestaurantId(command.restaurantId());
    }
}
