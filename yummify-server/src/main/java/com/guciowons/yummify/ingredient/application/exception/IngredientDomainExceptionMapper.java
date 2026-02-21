package com.guciowons.yummify.ingredient.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;

import java.util.Map;

@ExceptionMapper
public class IngredientDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case IngredientNotFoundException ex -> mapIngredientNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapIngredientNotFoundException(IngredientNotFoundException exception) {
        return ApiException.notFound(
                exception,
                IngredientErrorMessage.INGREDIENT_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId().value().toString())
        );
    }
}
