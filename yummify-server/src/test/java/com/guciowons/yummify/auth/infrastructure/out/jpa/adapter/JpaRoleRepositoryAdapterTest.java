package com.guciowons.yummify.auth.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.JpaRole;
import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.mapper.JpaRoleMapper;
import com.guciowons.yummify.auth.infrastructure.out.jpa.repository.JpaRoleRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRole;
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

}