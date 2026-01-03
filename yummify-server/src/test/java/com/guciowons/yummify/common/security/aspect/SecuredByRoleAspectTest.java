package com.guciowons.yummify.common.security.aspect;

import com.guciowons.yummify.common.security.application.SecuredByRole;
import com.guciowons.yummify.common.security.application.SecuredByRoleAspect;
import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.common.security.domain.AccessDeniedException;
import com.guciowons.yummify.common.security.domain.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecuredByRoleAspectTest {
    @InjectMocks
    private SecuredByRoleAspect aspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private SecuredByRole securedByRole;

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAllowAccessWhenUserHasTheSameRole() throws Throwable {
        setupSecurityContextHolder("ADMIN", true);

        when(securedByRole.value()).thenReturn(UserRole.ADMIN);
        when(joinPoint.proceed()).thenReturn("OK");

        Object result = aspect.checkAuthorization(joinPoint, securedByRole);

        assertEquals("OK", result);
    }

    @Test
    void shouldAllowAccessWhenUserHasHigherRole() throws Throwable {
        setupSecurityContextHolder("ADMIN", true);

        when(securedByRole.value()).thenReturn(UserRole.OWNER);
        when(joinPoint.proceed()).thenReturn("OK");

        Object result = aspect.checkAuthorization(joinPoint, securedByRole);

        assertEquals("OK", result);
    }

    @Test
    void shouldDenyAccessWhenUserDoesNotHaveRole() throws Throwable {
        setupSecurityContextHolder("OWNER", true);

        when(securedByRole.value()).thenReturn(UserRole.ADMIN);

        assertThrows(AccessDeniedException.class, () -> aspect.checkAuthorization(joinPoint, securedByRole));

        verify(joinPoint, never()).proceed();
    }

    @Test
    void shouldThrowWhenAuthenticationIsNull() {
        assertThrows(UnauthorizedException.class, () -> aspect.checkAuthorization(joinPoint, securedByRole));
    }

    @Test
    void shouldThrowWhenNotAuthenticated() {
        setupSecurityContextHolder("ADMIN", false);

        assertThrows(UnauthorizedException.class, () -> aspect.checkAuthorization(joinPoint, securedByRole));
    }

    private void setupSecurityContextHolder(String role, boolean isAuthenticated) {
        JwtAuthenticationToken auth = mock(JwtAuthenticationToken.class);
        when(auth.isAuthenticated()).thenReturn(isAuthenticated);

        if (isAuthenticated) {
            when(auth.getAuthorities()).thenReturn(List.of(new SimpleGrantedAuthority(role)));
        }

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);
    }
}