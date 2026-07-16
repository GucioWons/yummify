package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.RoleFacadePort;
import com.guciowons.yummify.auth.application.model.CreateRoleCommand;
import com.guciowons.yummify.auth.application.model.GetAllRolesQuery;
import com.guciowons.yummify.auth.application.model.mapper.RoleCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateRoleUsecase;
import com.guciowons.yummify.auth.application.usecase.GetAllRolesUsecase;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class RoleFacade implements RoleFacadePort {
    private final CreateRoleUsecase createRoleUsecase;
    private final GetAllRolesUsecase getAllRolesUsecase;
    private final RoleCommandMapper roleCommandMapper;

    @Override
    public UUID createAndGetId(UUID restaurantId, Map<String, String> name, Set<String> parameters) {
        return create(restaurantId, name, parameters).getId().value();
    }

    @Override
    public Role create(UUID restaurantId, Map<String, String> name, Set<String> permissions) {
        CreateRoleCommand command = roleCommandMapper.toCreateRoleCommand(restaurantId, name, permissions);
        return createRoleUsecase.create(command);
    }

    @Override
    public List<Role> getAll(UUID restaurantId) {
        GetAllRolesQuery query = roleCommandMapper.toGetAllRolesQuery(restaurantId);
        return getAllRolesUsecase.getAllRoles(query);
    }
}
