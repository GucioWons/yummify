package com.guciowons.yummify.common.security.application;

import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.common.security.domain.AccessDeniedException;
import com.guciowons.yummify.common.security.domain.UnauthorizedException;
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
            throw new UnauthorizedException();
        }

        List<UserRole> userRoles = authentication.getAuthorities().stream()
                .map(authority -> UserRole.valueOf(authority.getAuthority()))
                .toList();

        if (!hasAccess(userRoles, securedByRole.value())) {
            throw new AccessDeniedException();
        }

        return joinPoint.proceed();
    }

    private boolean hasAccess(List<UserRole> roles, UserRole requiredRole) {
        return roles.stream().anyMatch(requiredRole::hasRole);
    }
}
