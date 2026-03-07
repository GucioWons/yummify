package com.guciowons.yummify.menu.application.entry.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.entry.model.UpdateMenuEntryCommand;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateMenuEntryUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuEntry update(UpdateMenuEntryCommand command) {
        MenuVersion draft = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        MenuEntry updatedEntry = draft.updateSectionEntry(command.sectionId(), command.id(), command.dishId(), command.price());

        menuVersionRepository.save(draft);

        return updatedEntry;
    }
}
