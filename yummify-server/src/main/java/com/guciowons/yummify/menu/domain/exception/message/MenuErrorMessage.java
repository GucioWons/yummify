package com.guciowons.yummify.menu.domain.exception.message;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuErrorMessage implements ErrorMessage {
    DRAFT_MENU_VERSION_NOT_FOUND("Could not find draft menu version"),
    PUBLISHED_MENU_VERSION_NOT_FOUND("Could not find published menu version"),
    ARCHIVED_MENU_VERSION_NOT_FOUND("Could not find archived menu version with ID '{{id}}'"),
    MENU_DISHES_NOT_FOUND_BY_ID("Could not find dishes with IDs '{{ids}}' for menu"),
    MENU_SECTION_NOT_FOUND_BY_ID("Could not find menu section with ID '{{id}}'"),
    MENU_ENTRY_NOT_FOUND_BY_ID("Could not find menu entry with ID '{{id}}'"),
    CANNOT_UPDATE_MENU_SECTION_POSITION("Could not update menu section position"),
    MENU_VERSION_IS_NOT_DRAFT("Menu version is not draft"),
    MENU_VERSION_ALREADY_EXISTS("Menu version already exists"),
    MENU_VERSION_IS_NOT_PUBLISHED("Menu version is not published");

    private final String message;
}
