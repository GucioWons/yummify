package com.guciowons.yummify.menu.application.version.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.version.model.RestoreMenuVersionCommand;
import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class RestoreMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion restore(RestoreMenuVersionCommand command) {
        MenuVersion archivedToRestore = menuVersionLookupService.getArchivedByIdAndRestaurantId(
                command.id(),
                command.restaurantId()
        );

        MenuVersion draft = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        draft.restoreFrom(archivedToRestore);

        menuVersionRepository.save(draft);

        return draft;
    }
}
