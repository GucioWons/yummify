package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.auth.domain.exception.message.AuthErrorMessage;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import lombok.Getter;

@Getter
public class RoleNotFoundException extends AuthDomainException {
    private final Role.Id id;

    public RoleNotFoundException(Role.Id id) {
        super(AuthErrorMessage.ROLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
