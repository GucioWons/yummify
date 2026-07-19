package com.guciowons.yummify.menu.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import com.guciowons.yummify.menu.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MenuExceptionStatusResolver implements ExceptionStatusResolver {
    @Override
    public boolean supports(DomainException exception) {
        return exception instanceof MenuDomainException;
    }

    @Override
    public HttpStatus resolve(DomainException exception) {
        return switch (exception) {
            case DraftMenuVersionNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case PublishedMenuVersionNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case ArchivedMenuNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case MenuSectionNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case MenuEntryNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case CannotUpdateMenuSectionPositionException ignored -> HttpStatus.BAD_REQUEST;
            case MenuVersionIsNotDraftException ignored -> HttpStatus.CONFLICT;
            case MenuVersionAlreadyExistsException ignored -> HttpStatus.CONFLICT;
            case MenuVersionIsNotPublishedException ignored -> HttpStatus.CONFLICT;
            case MenuEntryNotFoundByDishException ignored -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
