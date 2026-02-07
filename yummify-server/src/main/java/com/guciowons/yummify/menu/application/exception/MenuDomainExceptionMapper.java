package com.guciowons.yummify.menu.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.application.ApiException;
import com.guciowons.yummify.common.exception.application.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.exception.DraftMenuVersionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.PublishedMenuVersionNotFoundException;

import java.util.Map;

@ExceptionMapper
public class MenuDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case DraftMenuVersionNotFoundException ex -> mapDraftMenuVersionNotFoundException(ex);
            case PublishedMenuVersionNotFoundException ex -> mapPublishedMenuVersionNotFoundException(ex);
            case MenuSectionNotFoundException ex -> mapMenuSectionNotFoundException(ex);
            case MenuEntryNotFoundException ex -> mapMenuEntryNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapDraftMenuVersionNotFoundException(DraftMenuVersionNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.DRAFT_MENU_VERSION_NOT_FOUND,
                Map.of()
        );
    }

    private ApiException mapPublishedMenuVersionNotFoundException(PublishedMenuVersionNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.PUBLISHED_MENU_VERSION_NOT_FOUND,
                Map.of()
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
