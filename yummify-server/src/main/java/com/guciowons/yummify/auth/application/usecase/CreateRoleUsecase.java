package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.CreateRoleCommand;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateRoleUsecase {
    private final RoleRepository roleRepository;

    public Role create(CreateRoleCommand command) {
        Role role = Role.create(command.restaurantId(), command.name(), command.permissions());
        roleRepository.save(role);
        return role;
    }
}
