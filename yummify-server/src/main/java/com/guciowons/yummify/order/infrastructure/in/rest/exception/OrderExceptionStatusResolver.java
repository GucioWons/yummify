package com.guciowons.yummify.order.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import com.guciowons.yummify.order.domain.exception.OrderDomainException;
import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderExceptionStatusResolver implements ExceptionStatusResolver {
    @Override
    public boolean supports(DomainException exception) {
        return exception instanceof OrderDomainException;
    }

    @Override
    public HttpStatus resolve(DomainException exception) {
        return switch (exception) {
            case OrderTableNotFoundException ignored -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
