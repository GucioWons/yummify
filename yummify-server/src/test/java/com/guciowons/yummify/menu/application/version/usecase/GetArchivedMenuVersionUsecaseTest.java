package com.guciowons.yummify.menu.application.version.usecase;

import com.guciowons.yummify.menu.application.version.service.MenuVersionLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenGetArchivedMenuVersionQuery;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetArchivedMenuVersionUsecaseTest {
    private final MenuVersionLookupService menuVersionLookupService = mock(MenuVersionLookupService.class);

    private final GetArchivedMenuVersionUsecase underTest = new GetArchivedMenuVersionUsecase(menuVersionLookupService);

    @Test
    void shouldGetArchivedMenuVersion() {
        // given
        var query = givenGetArchivedMenuVersionQuery();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionLookupService.getArchivedByIdAndRestaurantId(query.id(), query.restaurantId()))
                .thenReturn(menuVersion);

        // when
        var result = underTest.get(query);

        // then
        assertThat(result).isEqualTo(menuVersion);
    }
}