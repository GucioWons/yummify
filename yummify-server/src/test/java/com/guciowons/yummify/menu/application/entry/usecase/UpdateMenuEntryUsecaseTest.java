package com.guciowons.yummify.menu.application.entry.usecase;

import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenUpdateMenuEntryCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateMenuEntryUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final UpdateMenuEntryUsecase underTest = new UpdateMenuEntryUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldUpdateMenuEntry() {
        // given
        var draft = givenMenuVersion(1);
        var section = draft.addSection(givenMenuSectionName(1));
        var existingEntry = givenMenuEntry(1);
        section.addEntry(existingEntry);
        var command = givenUpdateMenuEntryCommand(section.getId());

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.update(command);

        // then
        assertThat(result.getDishId()).isEqualTo(command.dishId());
        assertThat(result.getPrice()).isEqualTo(command.price());
    }
}
