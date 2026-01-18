package com.guciowons.yummify.restaurant.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.application.ApiException;
import com.guciowons.yummify.common.exception.application.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;

import java.util.Map;

@ExceptionMapper
public class RestaurantDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case RestaurantNotFoundException ex -> mapRestaurantNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapRestaurantNotFoundException(RestaurantNotFoundException exception) {
        return ApiException.notFound(
                exception,
                RestaurantErrorMessage.RESTAURANT_NOT_FOUND_BY_ID,
                Map.of("id", exception.getId())
        );
    }
}
