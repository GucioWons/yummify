package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenGetMenuVersionQuery;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetDraftMenuVersionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);

    private final GetDraftMenuVersionUsecase underTest = new GetDraftMenuVersionUsecase(menuVersionLookupService);

    @Test
    void shouldGetDraftMenuVersion() {
        // given
        var query = givenGetMenuVersionQuery();
        var draft = givenMenuVersion(1);

        when(menuVersionLookupService.getDraftByRestaurantId(query.restaurantId())).thenReturn(draft);

        // when
        var result = underTest.get(query);

        // then
        verify(menuVersionLookupService).getDraftByRestaurantId(query.restaurantId());

        assertThat(result).isEqualTo(draft);
    }
}