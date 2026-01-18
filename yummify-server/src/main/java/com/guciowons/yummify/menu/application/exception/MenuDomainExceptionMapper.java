package com.guciowons.yummify.menu.application.exception;

import com.guciowons.yummify.common.exception.application.ApiException;
import com.guciowons.yummify.common.exception.application.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.exception.*;

import java.util.Map;
import java.util.UUID;

public class MenuDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case MenuNotFoundException ex -> mapMenuNotFoundException(ex);
            case MenuDishesNotFoundException ex -> mapMenuDishesNotFoundException(ex);
            case MenuSectionNotFoundException ex -> mapMenuSectionNotFoundException(ex);
            case MenuEntryNotFoundException ex -> mapMenuEntryNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapMenuNotFoundException(MenuNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.MENU_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId())
        );
    }

    private ApiException mapMenuDishesNotFoundException(MenuDishesNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.MENU_DISHES_NOT_FOUND_BY_ID,
                Map.of("ids", String.join(", ", exception.getIds().stream().map(UUID::toString).toList()))
        );
    }

    private ApiException mapMenuSectionNotFoundException(MenuSectionNotFoundException exception) {
        return ApiException.conflict(
                exception,
                MenuErrorMessage.MENU_SECTION_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId())
        );
    }

    private ApiException mapMenuEntryNotFoundException(MenuEntryNotFoundException exception) {
        return ApiException.conflict(
                exception,
                MenuErrorMessage.MENU_ENTRY_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId())
        );
    }
}
