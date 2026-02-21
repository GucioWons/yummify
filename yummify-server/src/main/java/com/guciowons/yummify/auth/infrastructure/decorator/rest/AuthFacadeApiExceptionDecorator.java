package com.guciowons.yummify.auth.infrastructure.decorator.rest;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.auth.infrastructure.in.rest.exception.AuthExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AuthFacadeApiExceptionDecorator implements AuthFacadePort {
    private final AuthFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = AuthExceptionMapper.class)
    public UUID createUser(String email, String username, String firstName, String lastName, UUID restaurantId, boolean withPassword) {
        return delegate.createUser(email, username, firstName, lastName, restaurantId, withPassword);
    }

    @Override
    @HandleDomainExceptions(handler = AuthExceptionMapper.class)
    public String generateOtp(UUID userId) {
        return delegate.generateOtp(userId);
    }
}
