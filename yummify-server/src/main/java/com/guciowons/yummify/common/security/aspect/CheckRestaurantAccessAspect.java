package com.guciowons.yummify.common.security.aspect;

import com.guciowons.yummify.config.security.CustomAuthenticationToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class CheckRestaurantAccessAspect {
    @Around("@annotation(com.guciowons.yummify.common.security.aspect.CheckRestaurantAccess)")
    public Object checkRestaurantAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof CustomAuthenticationToken token)) {
            throw new AccessDeniedException("Invalid authentication");
        }

        UUID allowedRestaurantId = token.getRestaurantId();

        UUID restaurantIdParam = getRestaurantIdArgument(joinPoint);

        if (!allowedRestaurantId.equals(restaurantIdParam)) {
            throw new AccessDeniedException("Access denied to restaurant");
        }

        return joinPoint.proceed();
    }

    private UUID getRestaurantIdArgument(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < paramAnnotations.length; i++) {
            for (Annotation a : paramAnnotations[i]) {
                if (a instanceof RestaurantId) {
                    if (args[i] instanceof UUID restaurantId) {
                        return restaurantId;
                    }
                }
            }
        }
        throw new IllegalArgumentException("No restaurant id found");
    }
}
