package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.RestoreMenuVersionCommand;
import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.exception.ArchivedMenuNotFoundException;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class RestoreMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion restore(RestoreMenuVersionCommand command) {
        MenuVersion archivedToRestore = menuVersionRepository
                .findArchivedByIdAndRestaurantId(command.id(), command.restaurantId())
                .orElseThrow(() -> new ArchivedMenuNotFoundException(command.id()));

        MenuVersion draft = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        draft.restoreFrom(archivedToRestore);

        menuVersionRepository.save(draft);

        return draft;
    }
}
