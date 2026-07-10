package com.guciowons.yummify.common.security.application;

import com.guciowons.yummify.common.security.domain.AccessDeniedException;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.common.security.domain.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class SecuredByPermissionAspect {
    @Around("@annotation(securedByPermission)")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint, SecuredByPermission securedByPermission) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException();
        }

        Set<Permission> userPermissions = authentication.getAuthorities().stream()
                .map(authority -> Permission.valueOf(authority.getAuthority()))
                .collect(Collectors.toSet());

        if (!hasAccess(userPermissions, securedByPermission.value())) {
            throw new AccessDeniedException();
        }

        return joinPoint.proceed();
    }

    private boolean hasAccess(Set<Permission> userPermissions, Permission requiredPermission) {
        return userPermissions.stream().anyMatch(userPermission -> userPermission.implies(requiredPermission));
    }
}
