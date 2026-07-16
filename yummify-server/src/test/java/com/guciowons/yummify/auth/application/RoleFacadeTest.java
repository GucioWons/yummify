package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.mapper.RoleCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateRoleUsecase;
import com.guciowons.yummify.auth.application.usecase.GetAllRolesUsecase;
import com.guciowons.yummify.common.security.domain.Permission;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenCreateRoleCommand;
import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGetAllRolesQuery;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRole;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRoleRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RoleFacadeTest {
    private final CreateRoleUsecase createRoleUsecase = mock(CreateRoleUsecase.class);
    private final GetAllRolesUsecase getAllRolesUsecase = mock(GetAllRolesUsecase.class);
    private final RoleCommandMapper roleCommandMapper = mock(RoleCommandMapper.class);

    private final RoleFacade underTest = new RoleFacade(createRoleUsecase, getAllRolesUsecase, roleCommandMapper);

    @Test
    void shouldCreateRoleAndGetId() {
        // given
        var restaurantId = givenRoleRestaurantId(1).value();
        var name = Map.of("EN", "Role");
        var permissions = Collections.singleton(Permission.OWNER.name());
        var command = givenCreateRoleCommand();
        var role = givenRole(1);

        when(roleCommandMapper.toCreateRoleCommand(restaurantId, name, permissions)).thenReturn(command);
        when(createRoleUsecase.create(command)).thenReturn(role);

        // when
        var result = underTest.createAndGetId(restaurantId, name, permissions);

        // then
        verify(roleCommandMapper).toCreateRoleCommand(restaurantId, name, permissions);
        verify(createRoleUsecase).create(command);

        assertThat(result).isEqualTo(role.getId().value());
    }

    @Test
    void shouldCreateRole() {
        // given
        var restaurantId = givenRoleRestaurantId(1).value();
        var name = Map.of("EN", "Role");
        var permissions = Collections.singleton(Permission.OWNER.name());
        var command = givenCreateRoleCommand();
        var role = givenRole(1);

        when(roleCommandMapper.toCreateRoleCommand(restaurantId, name, permissions)).thenReturn(command);
        when(createRoleUsecase.create(command)).thenReturn(role);

        // when
        var result = underTest.create(restaurantId, name, permissions);

        // then
        verify(roleCommandMapper).toCreateRoleCommand(restaurantId, name, permissions);
        verify(createRoleUsecase).create(command);

        assertThat(result).isEqualTo(role);
    }

    @Test
    void shouldGetAllRoles() {
        // given
        var restaurantId = givenRoleRestaurantId(1).value();
        var query = givenGetAllRolesQuery();
        var expectedResult = List.of(givenRole(1));

        when(roleCommandMapper.toGetAllRolesQuery(restaurantId)).thenReturn(query);
        when(getAllRolesUsecase.getAllRoles(query)).thenReturn(expectedResult);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(roleCommandMapper).toGetAllRolesQuery(restaurantId);
        verify(getAllRolesUsecase).getAllRoles(query);

        assertThat(result).isEqualTo(expectedResult);
    }
}
