package com.guciowons.yummify.table.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;

import java.util.Map;

@ExceptionMapper
public class TableDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case TableNotFoundException ex -> mapTableNotFoundException(ex);
            case TableExistsByNameException ex -> mapTableExistsByNameException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapTableNotFoundException(TableNotFoundException exception) {
        return ApiException.notFound(
                exception,
                TableErrorMessage.TABLE_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapTableExistsByNameException(TableExistsByNameException exception) {
        return ApiException.conflict(
                exception,
                TableErrorMessage.TABLE_EXISTS_BY_NAME,
                Map.of("name", exception.getName().value())
        );
    }
}
