package com.guciowons.yummify.auth;

import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface(name = "AuthFacadePort")
public interface AuthFacadePort {
    UUID createUser(String email, String username, String firstName, String lastName, UUID restaurantId, boolean withPassword);

    String generateOtp(UUID userId);
}
