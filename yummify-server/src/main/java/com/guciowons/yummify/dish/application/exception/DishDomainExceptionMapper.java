package com.guciowons.yummify.dish.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;

import java.util.Map;
import java.util.UUID;

@ExceptionMapper
public class DishDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case DishNotFoundException ex -> mapDishNotFoundException(ex);
            case DishIngredientsNotFoundException ex -> mapDishIngredientsNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapDishNotFoundException(DishNotFoundException exception) {
        return ApiException.notFound(
                exception,
                DishErrorMessage.DISH_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapDishIngredientsNotFoundException(DishIngredientsNotFoundException exception) {
        return ApiException.notFound(
                exception,
                DishErrorMessage.DISH_INGREDIENTS_NOT_FOUND_BY_IDS,
                Map.of("ids", String.join(", ", exception.getIds().stream().map(UUID::toString).toList()))
        );
    }
}
