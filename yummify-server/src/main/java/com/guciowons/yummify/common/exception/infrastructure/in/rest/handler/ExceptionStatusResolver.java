package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public interface ExceptionStatusResolver {
    boolean supports(DomainException exception);

    HttpStatus resolve(DomainException exception);
}
