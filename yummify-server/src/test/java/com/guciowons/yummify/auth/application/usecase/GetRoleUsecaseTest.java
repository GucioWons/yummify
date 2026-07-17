package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.service.RoleLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGetRoleQuery;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetRoleUsecaseTest {
    private final RoleLookupService roleLookupService = mock(RoleLookupService.class);
    private final GetRoleUsecase underTest = new GetRoleUsecase(roleLookupService);

    @Test
    void shouldGetRole() {
        // given
        var query = givenGetRoleQuery();
        var role = givenRole(1);

        when(roleLookupService.getByIdAndRestaurantId(query.id(), query.restaurantId())).thenReturn(role);

        // when
        var result = underTest.getRole(query);

        // then
        verify(roleLookupService).getByIdAndRestaurantId(query.id(), query.restaurantId());

        assertThat(result).isEqualTo(role);
    }
}