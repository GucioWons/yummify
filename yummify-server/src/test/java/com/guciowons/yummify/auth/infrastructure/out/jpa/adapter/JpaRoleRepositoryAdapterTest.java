package com.guciowons.yummify.auth.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.JpaRole;
import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.mapper.JpaRoleMapper;
import com.guciowons.yummify.auth.infrastructure.out.jpa.repository.JpaRoleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaRoleRepositoryAdapterTest {
    private final JpaRoleRepository jpaRoleRepository = mock(JpaRoleRepository.class);
    private final JpaRoleMapper jpaRoleMapper = mock(JpaRoleMapper.class);

    private final JpaRoleRepositoryAdapter underTest = new JpaRoleRepositoryAdapter(jpaRoleRepository, jpaRoleMapper);

    @Test
    void shouldSaveRole() {
        // given
        var role = givenRole(1);
        var jpaRole = new JpaRole();

        when(jpaRoleMapper.toJpa(role)).thenReturn(jpaRole);

        // when
        underTest.save(role);

        // then
        verify(jpaRoleMapper).toJpa(role);
        verify(jpaRoleRepository).save(jpaRole);
    }

    @Test
    void shouldFindRole() {
        // given
        var id = givenRoleId(1);
        var restaurantId = givenRoleRestaurantId(1);
        var jpaRole = new JpaRole();
        var role = givenRole(1);

        when(jpaRoleRepository.findByIdAndRestaurantId(id.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaRole));
        when(jpaRoleMapper.toDomain(jpaRole)).thenReturn(role);

        // when
        var result = underTest.findByIdAndRestaurantId(id, restaurantId);

        // then
        verify(jpaRoleRepository).findByIdAndRestaurantId(id.value(), restaurantId.value());
        verify(jpaRoleMapper).toDomain(jpaRole);

        assertThat(result).hasValue(role);
    }

    @Test
    void shouldFindAllRoles() {
        // given
        var restaurantId = givenRoleRestaurantId(1);
        var jpaRoles = List.of(new JpaRole(), new JpaRole(), new JpaRole());
        var roles = List.of(givenRole(1), givenRole(2), givenRole(3));

        when(jpaRoleRepository.findAllByRestaurantId(restaurantId.value())).thenReturn(jpaRoles);
        when(jpaRoleMapper.toDomain(jpaRoles.getFirst())).thenReturn(roles.getFirst());
        when(jpaRoleMapper.toDomain(jpaRoles.get(1))).thenReturn(roles.get(1));
        when(jpaRoleMapper.toDomain(jpaRoles.get(2))).thenReturn(roles.get(2));

        // when
        var result = underTest.findAllByRestaurantId(restaurantId);

        // then
        verify(jpaRoleRepository).findAllByRestaurantId(restaurantId.value());
        verify(jpaRoleMapper).toDomain(jpaRoles.getFirst());
        verify(jpaRoleMapper).toDomain(jpaRoles.get(1));
        verify(jpaRoleMapper).toDomain(jpaRoles.get(2));

        assertThat(result).isEqualTo(roles);
    }
}