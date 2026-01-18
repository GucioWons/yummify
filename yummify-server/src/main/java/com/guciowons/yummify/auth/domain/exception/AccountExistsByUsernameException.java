package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class AccountExistsByUsernameException extends DomainException {
    public AccountExistsByUsernameException() {
        super("Account exists by username");
    }
}
