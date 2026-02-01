package com.guciowons.yummify.common.exception.application.handler;

import com.guciowons.yummify.common.exception.application.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class DomainExceptionHandler {
    private final DomainExceptionMapper domainExceptionMapper;

    public <T> T handle(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (DomainException ex) {
            throw domainExceptionMapper.mapToApiException(ex);
        }
    }

    public void handle(Runnable runnable) {
        try {
            runnable.run();
        } catch (DomainException ex) {
            throw domainExceptionMapper.mapToApiException(ex);
        }
    }
}
