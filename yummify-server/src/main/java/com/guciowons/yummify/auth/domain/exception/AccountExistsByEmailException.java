package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.auth.domain.exception.message.AuthErrorMessage;

public class AccountExistsByEmailException extends AuthDomainException {
    public AccountExistsByEmailException() {
        super(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_EMAIL);
    }
}
