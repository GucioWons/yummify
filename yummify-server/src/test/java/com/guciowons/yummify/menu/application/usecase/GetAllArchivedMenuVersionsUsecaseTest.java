package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenGetMenuVersionQuery;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllArchivedMenuVersionsUsecaseTest {
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final GetAllArchivedMenuVersionsUsecase underTest = new GetAllArchivedMenuVersionsUsecase(
            menuVersionRepository
    );

    @Test
    void shouldGetAllMenuVersions() {
        // given
        var query = givenGetMenuVersionQuery();
        var menuVersions = List.of(givenMenuVersion(1), givenMenuVersion(2), givenMenuVersion(3));

        when(menuVersionRepository.findAllArchivedByRestaurantId(query.restaurantId())).thenReturn(menuVersions);

        // when
        var result = underTest.getAll(query);

        // then
        verify(menuVersionRepository).findAllArchivedByRestaurantId(query.restaurantId());

        assertThat(result).isEqualTo(menuVersions);
    }

}