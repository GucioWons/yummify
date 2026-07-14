package com.guciowons.yummify.auth.application.service;

import com.guciowons.yummify.auth.domain.exception.RoleNotFoundException;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RoleLookupServiceTest {
    private final RoleRepository roleRepository = mock(RoleRepository.class);

    private final RoleLookupService underTest = new RoleLookupService(roleRepository);

    @Test
    void shouldGetRole_WhenExists() {
        // given
        var role = givenRole(1);
        var roleId = role.getId();
        var restaurantId = role.getRestaurantId();

        when(roleRepository.findByIdAndRestaurantId(roleId, restaurantId)).thenReturn(Optional.of(role));

        // when
        var result = underTest.getByIdAndRestaurantId(roleId, restaurantId);

        // then
        verify(roleRepository).findByIdAndRestaurantId(roleId, restaurantId);

        assertThat(result).isEqualTo(role);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var roleId = givenRoleId(1);
        var restaurantId = givenRoleRestaurantId(1);

        when(roleRepository.findByIdAndRestaurantId(roleId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(roleId, restaurantId))
                .isInstanceOf(RoleNotFoundException.class);

        // then
        verify(roleRepository).findByIdAndRestaurantId(roleId, restaurantId);
    }

}