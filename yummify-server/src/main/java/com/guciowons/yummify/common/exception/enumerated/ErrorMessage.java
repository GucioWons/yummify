package com.guciowons.yummify.common.exception.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    RESTAURANT_NOT_FOUND_BY_ID("Could not find restaurant with ID '{{id}}'"),

    TABLE_EXISTS_BY_NAME("Table with name '{{name}}' already exists"),
    TABLE_NOT_FOUND_BY_ID("Could not find table with ID '{{id}}'"),

    INGREDIENT_NOT_FOUND_BY_ID("Could not find ingredient with ID '{{id}}'"),
    INGREDIENTS_NOT_FOUND_BY_IDS("Could not find ingredients with IDs '{{ids}}'"),

    DISH_NOT_FOUND_BY_ID("Could not find dish with ID '{{id}}'"),

    MENU_NOT_FOUND_BY_ID("Could not find menu with ID '{{id}}'"),
    MENU_SECTION_NOT_FOUND_BY_ID("Could not find menu section with ID '{{id}}'"),
    MENU_ENTRY_NOT_FOUND_BY_ID("Could not find menu entry with ID '{{id}}'"),
    NO_ACTIVE_MENU("Could not find active menu"),
    MENU_IS_ACTIVE("Menu is active"),
    MENU_IS_NOT_ACTIVE("Menu is not active"),

    CANNOT_GET_FILE("Could not get file"),

    KEYCLOAK_ACCOUNT_EXISTS_BY_EMAIL("Account with this email already exists"),
    KEYCLOAK_ACCOUNT_EXISTS_BY_USERNAME("Account with this username already exists"),

    ACCESS_DENIED("Access denied"),
    UNAUTHORIZED("You are not logged in"),

    VALIDATION_NOT_NULL("This field is required"),
    VALIDATION_NULL("This field has to be null"),

    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
