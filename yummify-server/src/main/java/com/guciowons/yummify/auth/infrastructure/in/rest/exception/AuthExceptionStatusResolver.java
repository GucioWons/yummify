package com.guciowons.yummify.auth.infrastructure.in.rest.exception;

import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.domain.exception.AuthDomainException;
import com.guciowons.yummify.auth.domain.exception.RoleNotFoundException;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthExceptionStatusResolver implements ExceptionStatusResolver {
    @Override
    public boolean supports(DomainException exception) {
        return exception instanceof AuthDomainException;
    }

    @Override
    public HttpStatus resolve(DomainException exception) {
        return switch (exception) {
            case AccountExistsByEmailException ignored -> HttpStatus.CONFLICT;
            case AccountExistsByUsernameException ignored -> HttpStatus.CONFLICT;
            case RoleNotFoundException ignored -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
