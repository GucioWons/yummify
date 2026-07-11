package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends DomainException {
    private final Role.Id id;

    public RoleNotFoundException(Role.Id id) {
        super("Role not found");
        this.id = id;
    }
}
