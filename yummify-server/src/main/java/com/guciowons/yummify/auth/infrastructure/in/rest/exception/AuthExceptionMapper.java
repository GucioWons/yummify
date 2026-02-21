package com.guciowons.yummify.auth.infrastructure.in.rest.exception;

import com.guciowons.yummify.auth.domain.exception.message.AuthErrorMessage;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;

@ExceptionMapper
public class AuthExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case AccountExistsByEmailException ex -> mapAccountExistsByEmailException(ex);
            case AccountExistsByUsernameException ex -> mapAccountExistsByUsernameException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapAccountExistsByEmailException(AccountExistsByEmailException ex) {
        return ApiException.conflict(
                ex,
                AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_EMAIL,
                null
        );
    }

    private ApiException mapAccountExistsByUsernameException(AccountExistsByUsernameException ex) {
        return ApiException.conflict(
                ex,
                AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_USERNAME,
                null
        );
    }
}
