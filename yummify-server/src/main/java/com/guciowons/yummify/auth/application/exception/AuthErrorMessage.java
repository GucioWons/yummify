package com.guciowons.yummify.auth.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorMessage implements ErrorMessage {
    AUTH_ACCOUNT_EXISTS_BY_EMAIL("Account with this email already exists"),
    AUTH_ACCOUNT_EXISTS_BY_USERNAME("Account with this username already exists");

    private final String message;
}
