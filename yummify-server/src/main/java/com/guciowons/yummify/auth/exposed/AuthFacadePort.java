package com.guciowons.yummify.auth.exposed;

import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface(name = "AuthFacadePort")
public interface AuthFacadePort {
    UUID createUser(String email, String username, String firstName, String lastName, UUID restaurantId);

    UUID createUserWithPassword(String email, String username, String firstName, String lastName, UUID restaurantId);

    String generateOtp(UUID userId);
}
