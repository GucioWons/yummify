package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateMenuSectionUsecase {
    private final MenuVersionRepository menuVersionRepository;

    public void update(UpdateMenuSectionCommand command) {
        MenuVersion draft = menuVersionRepository.findDraftByRestaurantId(command.restaurantId())
                .orElseThrow();

        draft.updateSection(command.id(), command.name(), command.position(), command.entrySnapshots());

        menuVersionRepository.save(draft);
    }
}
