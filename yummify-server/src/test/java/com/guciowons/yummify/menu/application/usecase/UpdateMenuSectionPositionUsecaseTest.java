package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenUpdateMenuSectionPositionCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuSectionName;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateMenuSectionPositionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);
    private final UpdateMenuSectionPositionUsecase underTest = new UpdateMenuSectionPositionUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldUpdateMenuSectionPosition() {
        // given
        var draft = givenMenuVersion(1);
        var section1 = draft.addSection(givenMenuSectionName(1));
        var section2 = draft.addSection(givenMenuSectionName(1));
        var section3 = draft.addSection(givenMenuSectionName(1));
        var command = givenUpdateMenuSectionPositionCommand(section3.getId());

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.update(command);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(draft);

        assertThat(result).containsExactly(section1, section2, section3);
    }
}
