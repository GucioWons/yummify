package com.guciowons.yummify.menu.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.exception.*;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

import java.util.Map;

@ExceptionMapper
public class MenuDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case DraftMenuVersionNotFoundException ex -> mapDraftMenuVersionNotFoundException(ex);
            case PublishedMenuVersionNotFoundException ex -> mapPublishedMenuVersionNotFoundException(ex);
            case ArchivedMenuNotFoundException ex -> mapArchivedMenuNotFoundException(ex);
            case MenuSectionNotFoundException ex -> mapMenuSectionNotFoundException(ex);
            case MenuEntryNotFoundException ex -> mapMenuEntryNotFoundException(ex);
            case CannotUpdateMenuSectionPositionException ex -> mapCannotUpdateMenuSectionPositionException(ex);
            case MenuVersionIsNotDraftException ex -> mapMenuVersionIsNotDraftException(ex);
            case MenuVersionAlreadyExistsException ex -> mapMenuVersionAlreadyExistsException(ex);
            case MenuVersionIsNotPublishedException ex -> mapMenuVersionIsNotPublishedException(ex);
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

    private ApiException mapArchivedMenuNotFoundException(ArchivedMenuNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.ARCHIVED_MENU_VERSION_NOT_FOUND,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapMenuSectionNotFoundException(MenuSectionNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.MENU_SECTION_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapMenuEntryNotFoundException(MenuEntryNotFoundException exception) {
        return ApiException.notFound(
                exception,
                MenuErrorMessage.MENU_ENTRY_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapCannotUpdateMenuSectionPositionException(CannotUpdateMenuSectionPositionException ex) {
        return ApiException.badRequest(ex, MenuErrorMessage.CANNOT_UPDATE_MENU_SECTION_POSITION);
    }

    private ApiException mapMenuVersionIsNotDraftException(MenuVersionIsNotDraftException ex) {
        return ApiException.conflict(ex, MenuErrorMessage.MENU_VERSION_IS_NOT_DRAFT, Map.of());
    }

    private ApiException mapMenuVersionAlreadyExistsException(MenuVersionAlreadyExistsException ex) {
        return ApiException.conflict(ex, MenuErrorMessage.MENU_VERSION_ALREADY_EXISTS, Map.of());
    }

    private ApiException mapMenuVersionIsNotPublishedException(MenuVersionIsNotPublishedException ex) {
        return ApiException.conflict(ex, MenuErrorMessage.MENU_VERSION_IS_NOT_PUBLISHED, Map.of());
    }
}
