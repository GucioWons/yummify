package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenUpdateMenuSectionEntriesCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateMenuSectionEntriesUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final UpdateMenuSectionEntriesUsecase underTest = new UpdateMenuSectionEntriesUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldUpdateMenuSectionEntries() {
        // given
        var draft = givenMenuVersion(1);
        var section = draft.addSection(givenMenuSectionName(1));
        var command = givenUpdateMenuSectionEntriesCommand(section.getId());

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.update(command);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(draft);

        assertThat(result).isEqualTo(section);
    }
}
