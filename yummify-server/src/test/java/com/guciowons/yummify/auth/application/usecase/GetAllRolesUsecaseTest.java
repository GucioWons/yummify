package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGetAllRolesQuery;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRole;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllRolesUsecaseTest {
    private final RoleRepository roleRepository = mock(RoleRepository.class);

    private final GetAllRolesUsecase underTest = new GetAllRolesUsecase(roleRepository);

    @Test
    void shouldReturnAllRoles() {
        // given
        var query = givenGetAllRolesQuery();
        var expectedResult = List.of(givenRole(1));

        when(roleRepository.findAllByRestaurantId(query.restaurantId())).thenReturn(expectedResult);

        // when
        var result = underTest.getAllRoles(query);

        // then
        verify(roleRepository).findAllByRestaurantId(query.restaurantId());

        assertThat(result).isEqualTo(expectedResult);
    }

}