package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.CreateRoleCommand;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateRoleUsecase {
    public Role create(CreateRoleCommand command) {
        return Role.create(command.restaurantId(), command.name(), command.permissions());
    }
}
