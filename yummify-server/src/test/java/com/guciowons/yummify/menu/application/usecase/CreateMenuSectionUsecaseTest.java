package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenCreateMenuSectionCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateMenuSectionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final CreateMenuSectionUsecase underTest = new CreateMenuSectionUsecase(
            menuVersionLookupService,
            menuVersionRepository
    );

    @Test
    void shouldCreateMenuSection() {
        // given
        var command = givenCreateMenuSectionCommand();
        var draft = givenMenuVersion(1);

        when(menuVersionLookupService.getDraftByRestaurantId(command.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.create(command);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(draft);

        assertThat(result).isNotNull();
        assertThat(draft.getSections()).hasSize(1);
        assertThat(draft.getSections()).contains(result);
    }
}