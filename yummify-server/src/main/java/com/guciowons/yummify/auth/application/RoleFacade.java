package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.CreateRoleCommand;
import com.guciowons.yummify.auth.application.model.mapper.RoleCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateRoleUsecase;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class RoleFacade {
    private final CreateRoleUsecase createRoleUsecase;
    private final RoleCommandMapper roleCommandMapper;

    public Role create(UUID restaurantId, Map<String, String> name, Set<String> permissions) {
        CreateRoleCommand command = roleCommandMapper.toCreateRoleCommand(restaurantId, name, permissions);
        return createRoleUsecase.create(command);
    }
}
