package com.guciowons.yummify.common.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class SecuredByRoleAspect {
    @Around("@annotation(securedByRole)")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint, SecuredByRole securedByRole) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException();
        }

        List<UserRole> userRoles = authentication.getAuthorities().stream()
                .map(authority -> UserRole.valueOf(authority.getAuthority()))
                .toList();

        if (!hasAccess(userRoles, securedByRole.value())) {
            throw new RuntimeException();
        }

        return joinPoint.proceed();
    }

    private boolean hasAccess(List<UserRole> roles, UserRole requiredRole) {
        return roles.stream().anyMatch(role -> role.hasRole(requiredRole));
    }
}
