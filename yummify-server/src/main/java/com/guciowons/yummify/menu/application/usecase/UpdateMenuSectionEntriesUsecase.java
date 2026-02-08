package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionEntriesCommand;
import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateMenuSectionEntriesUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuSection update(UpdateMenuSectionEntriesCommand command) {
        MenuVersion draft = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        MenuSection updatedSection = draft.updateSectionEntries(command.id(), command.entrySnapshots());

        menuVersionRepository.save(draft);

        return updatedSection;
    }
}
