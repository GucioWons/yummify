package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenRestoreMenuVersionCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
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

        when(menuVersionLookupService.getArchivedByIdAndRestaurantId(command.id(), command.restaurantId()))
                .thenReturn(archivedToRestore);
        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.restore(command);

        // then
        verify(menuVersionLookupService).getArchivedByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(draft);

        assertThat(result).isEqualTo(draft);
    }
}
