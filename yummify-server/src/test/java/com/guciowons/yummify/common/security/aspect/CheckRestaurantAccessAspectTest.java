package com.guciowons.yummify.common.security.aspect;

import com.guciowons.yummify.common.security.logic.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckRestaurantAccessAspectTest {
    @InjectMocks
    private CheckRestaurantAccessAspect underTest;

    @Mock
    private TokenService tokenService;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Test
    void shouldProceedWhenRestaurantIdMatches() throws Throwable {
        UUID restaurantId = UUID.randomUUID();

        mockJoinPoint("securedMethod", new Object[]{restaurantId}, UUID.class);
        when(tokenService.getRestaurantId()).thenReturn(restaurantId);
        when(joinPoint.proceed()).thenReturn("OK");

        Object result = underTest.checkRestaurantAccess(joinPoint);

        assertEquals("OK", result);
    }

    @Test
    void shouldProceedWithProperlyAnnotatedRestaurantIdWhenThereAreTwoAnnotatedParams() throws Throwable {
        UUID restaurantId = UUID.randomUUID();
        UUID otherId = UUID.randomUUID();

        mockJoinPoint("securedMethodWithTwoAnnotatedParams", new Object[]{otherId, restaurantId}, UUID.class, UUID.class);
        when(tokenService.getRestaurantId()).thenReturn(restaurantId);
        when(joinPoint.proceed()).thenReturn("OK");

        Object result = underTest.checkRestaurantAccess(joinPoint);

        assertEquals("OK", result);
    }

    @Test
    void shouldThrowWhenRestaurantIdDoesNotMatch() throws Throwable {
        UUID allowedId = UUID.randomUUID();
        UUID differentId = UUID.randomUUID();

        mockJoinPoint("securedMethod", new Object[]{differentId}, UUID.class);
        when(tokenService.getRestaurantId()).thenReturn(allowedId);

        assertThrows(AccessDeniedException.class, () -> underTest.checkRestaurantAccess(joinPoint));
    }

    @Test
    void shouldThrowWhenRestaurantIdIsNotUUID() throws Throwable {
        UUID restaurantId = UUID.randomUUID();

        mockJoinPoint("securedMethodWithWrongType", new Object[]{"invalid"}, String.class);
        when(tokenService.getRestaurantId()).thenReturn(restaurantId);

        assertThrows(IllegalArgumentException.class, () -> underTest.checkRestaurantAccess(joinPoint));
    }

    @Test
    void shouldThrowWhenNoRestaurantIdParamPresent() throws Throwable {
        mockJoinPoint("methodWithoutAnnotation", new Object[]{"invalid"}, String.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.checkRestaurantAccess(joinPoint));
    }

    private void mockJoinPoint(String methodName, Object[] args, Class<?>... clazz) throws NoSuchMethodException {
        MethodSignature signature = mock(MethodSignature.class);
        Method method = TestService.class.getMethod(methodName, clazz);
        when(signature.getMethod()).thenReturn(method);
        when(joinPoint.getSignature()).thenReturn(signature);
        when(joinPoint.getArgs()).thenReturn(args);
    }

    static class TestService {
        public void securedMethod(@RestaurantId UUID restaurantId) {}

        public void securedMethodWithTwoAnnotatedParams(@PathVariable UUID notRestaurantId, @RestaurantId UUID restaurantId) {}

        public void securedMethodWithWrongType(@RestaurantId String restaurantId) {}

        public void methodWithoutAnnotation(String value) {}
    }
}