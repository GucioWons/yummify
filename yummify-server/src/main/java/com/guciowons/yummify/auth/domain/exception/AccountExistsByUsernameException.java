package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.auth.domain.exception.message.AuthErrorMessage;

public class AccountExistsByUsernameException extends AuthDomainException {
    public AccountExistsByUsernameException() {
        super(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_USERNAME);
    }
}
