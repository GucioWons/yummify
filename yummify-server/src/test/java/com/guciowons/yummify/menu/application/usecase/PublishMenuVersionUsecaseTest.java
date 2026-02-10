package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenPublishMenuVersionCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PublishMenuVersionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final PublishMenuVersionUsecase underTest = new PublishMenuVersionUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldPublishMenuVersionUsecaseAndArchivePrevious_WhenPreviousPublishedMenuVersionExists() {
        // given
        var command = givenPublishMenuVersionCommand();
        var draft = givenMenuVersion(1);
        var published = givenMenuVersion(2);
        published.publish();

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);
        when(menuVersionRepository.findPublishedByRestaurantId(command.restaurantId()))
                .thenReturn(Optional.of(published));

        // when
        var result = underTest.publish(command);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).findPublishedByRestaurantId(command.restaurantId());

        var savedCaptor = ArgumentCaptor.forClass(MenuVersion.class);
        verify(menuVersionRepository, times(3)).save(savedCaptor.capture());

        var savedVersions = savedCaptor.getAllValues();

        MenuVersion nextDraft = savedVersions.get(2);
        assertThat(savedVersions).containsExactly(published, draft, nextDraft);
        assertThat(nextDraft.getVersion()).isEqualTo(draft.getVersion() + 1);
        assertThat(nextDraft.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
        assertThat(published.getStatus()).isEqualTo(MenuVersion.Status.ARCHIVED);
        assertThat(result.getStatus()).isEqualTo(MenuVersion.Status.PUBLISHED);
    }

    @Test
    void shouldPublishMenuVersionUsecaseAndNotArchivePrevious_WhenPreviousPublishedMenuVersionNotExists() {
        // given
        var command = givenPublishMenuVersionCommand();
        var draft = givenMenuVersion(1);

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);
        when(menuVersionRepository.findPublishedByRestaurantId(command.restaurantId())).thenReturn(Optional.empty());

        // when
        var result = underTest.publish(command);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).findPublishedByRestaurantId(command.restaurantId());

        var savedCaptor = ArgumentCaptor.forClass(MenuVersion.class);
        verify(menuVersionRepository, times(2)).save(savedCaptor.capture());

        var savedVersions = savedCaptor.getAllValues();

        MenuVersion nextDraft = savedVersions.get(1);
        assertThat(savedVersions).containsExactly(draft, nextDraft);
        assertThat(nextDraft.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
        assertThat(result.getStatus()).isEqualTo(MenuVersion.Status.PUBLISHED);
        assertThat(nextDraft.getVersion()).isEqualTo(draft.getVersion() + 1);
    }
}
