package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.service.MenuVersionLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenGetMenuVersionQuery;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetPublishedMenuVersionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);

    private final GetPublishedMenuVersionUsecase underTest = new GetPublishedMenuVersionUsecase(menuVersionLookupService);

    @Test
    void shouldGetPublishedMenuVersion() {
        // given
        var query = givenGetMenuVersionQuery();
        var published = givenMenuVersion(1);

        when(menuVersionLookupService.getPublishedByRestaurantId(query.restaurantId())).thenReturn(published);

        // when
        var result = underTest.get(query);

        // then
        verify(menuVersionLookupService).getPublishedByRestaurantId(query.restaurantId());

        assertThat(result).isEqualTo(published);
    }
}
