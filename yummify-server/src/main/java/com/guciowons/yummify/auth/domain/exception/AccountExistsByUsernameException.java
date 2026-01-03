package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class AccountExistsByUsernameException extends DomainException {
    public AccountExistsByUsernameException() {
        super(new DomainError(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_USERNAME));
    }
}
