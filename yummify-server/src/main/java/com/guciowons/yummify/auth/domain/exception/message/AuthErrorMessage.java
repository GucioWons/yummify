package com.guciowons.yummify.auth.domain.exception.message;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorMessage implements ErrorMessage {
    AUTH_ACCOUNT_EXISTS_BY_EMAIL("Account with this email already exists"),
    AUTH_ACCOUNT_EXISTS_BY_USERNAME("Account with this username already exists"),
    ROLE_NOT_FOUND_BY_ID("Could not find dish with ID '{{id}}'");

    private final String message;
}
