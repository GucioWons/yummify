package com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleDomainExceptions {
    Class<? extends DomainExceptionMapper> handler();
}
