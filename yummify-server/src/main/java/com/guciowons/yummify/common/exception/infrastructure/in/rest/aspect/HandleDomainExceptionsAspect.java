package com.guciowons.yummify.common.exception.infrastructure.in.rest.aspect;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class HandleDomainExceptionsAspect {
    private final ApplicationContext applicationContext;

    @Around("@annotation(annotation)")
    public Object handle(ProceedingJoinPoint joinPoint, HandleDomainExceptions annotation) throws Throwable {
        DomainExceptionMapper mapper = applicationContext.getBean(annotation.handler());

        try {
            return joinPoint.proceed();
        } catch (DomainException exception) {
            throw mapper.mapToApiException(exception);
        }
    }
}
