package com.guciowons.yummify.menu.application.entry.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.entry.model.CreateMenuEntryCommand;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateMenuEntryUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuEntry create(CreateMenuEntryCommand command) {
        MenuVersion draft = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        MenuEntry newMenuEntry = MenuEntry.create(command.dishId(), command.price());
        draft.addSectionEntry(command.sectionId(), newMenuEntry);

        menuVersionRepository.save(draft);

        return newMenuEntry;
    }
}
