package com.guciowons.yummify.restaurant.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantDomainException;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestaurantExceptionStatusResolver implements ExceptionStatusResolver {
    @Override
    public boolean supports(DomainException exception) {
        return exception instanceof RestaurantDomainException;
    }

    @Override
    public HttpStatus resolve(DomainException exception) {
        return switch (exception) {
            case RestaurantNotFoundException ignored -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
