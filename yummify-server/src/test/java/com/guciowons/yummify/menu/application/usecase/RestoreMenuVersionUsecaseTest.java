package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.exception.ArchivedMenuNotFoundException;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenRestoreMenuVersionCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RestoreMenuVersionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final RestoreMenuVersionUsecase underTest = new RestoreMenuVersionUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldRestoreMenuVersion() {
        // given
        var command = givenRestoreMenuVersionCommand();
        var archivedToRestore = givenMenuVersion(1);
        var draft = givenMenuVersion(2);

        when(menuVersionRepository.findArchivedByIdAndRestaurantId(command.id(), command.restaurantId()))
                .thenReturn(Optional.of(archivedToRestore));
        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.restore(command);

        // then
        verify(menuVersionRepository).findArchivedByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(draft);

        assertThat(result).isEqualTo(draft);
    }

    @Test
    void shouldNotRestoreMenuVersionAndThrowException_WhenArchivedMenuVersionNotFound() {
        // given
        var command = givenRestoreMenuVersionCommand();

        when(menuVersionRepository.findArchivedByIdAndRestaurantId(command.id(), command.restaurantId()))
                .thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.restore(command)).isInstanceOf(ArchivedMenuNotFoundException.class);

        // then
        verify(menuVersionRepository).findArchivedByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(menuVersionLookupService, never()).getDraftByRestaurantId(any());
        verify(menuVersionRepository, never()).save(any());
    }
}
