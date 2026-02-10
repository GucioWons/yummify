package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.model.PublishMenuVersionCommand;
import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class PublishMenuVersionUsecase {
    private final MenuVersionLookupService menuVersionLookupService;
    private final MenuVersionRepository menuVersionRepository;

    public MenuVersion publish(PublishMenuVersionCommand command) {
        MenuVersion draftToPublish = menuVersionLookupService.getDraftByRestaurantId(command.restaurantId());

        menuVersionRepository.findPublishedByRestaurantId(command.restaurantId()).ifPresent(this::archiveMenuVersion);

        draftToPublish.publish();
        menuVersionRepository.save(draftToPublish);

        MenuVersion nextDraft = draftToPublish.createNextDraft();
        menuVersionRepository.save(nextDraft);

        return draftToPublish;
    }

    private void archiveMenuVersion(MenuVersion menuVersion) {
        menuVersion.archive();
        menuVersionRepository.save(menuVersion);
    }
}
