package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class AccountExistsByEmailException extends DomainException {
    public AccountExistsByEmailException() {
        super(new DomainError(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_EMAIL));
    }
}
