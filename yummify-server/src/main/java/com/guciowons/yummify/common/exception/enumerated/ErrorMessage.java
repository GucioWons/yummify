package com.guciowons.yummify.common.exception.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    RESTAURANT_NOT_FOUND_BY_ID("Could not find restaurant with ID {{id}}"),

    KEYCLOAK_ACCOUNT_EXISTS_BY_EMAIL("Account with this email already exists"),
    KEYCLOAK_ACCOUNT_EXISTS_BY_USERNAME("Account with this username already exists"),

    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
