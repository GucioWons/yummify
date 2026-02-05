package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateMenuSectionUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public MenuSection.Id createMenuSection(CreateMenuSectionCommand command) {
        MenuVersion draft = menuVersionRepository.findDraftByRestaurantId(command.restaurantId())
                .orElseThrow();

        MenuSection.Id sectionId = draft.addSection(command.name(), command.position());

        menuVersionRepository.save(draft);

        return sectionId;
    }
}
