package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class AccountExistsByEmailException extends DomainException {
    public AccountExistsByEmailException() {
        super("Account exists by email");
    }
}
