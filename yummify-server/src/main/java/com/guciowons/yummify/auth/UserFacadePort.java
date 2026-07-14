package com.guciowons.yummify.auth;

import com.guciowons.yummify.auth.domain.model.User;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "UserFacadePort")
public interface UserFacadePort {
    List<User> getAllUsers(UUID restaurantId);

    User createUser(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            UUID roleId,
            boolean withPassword
    );

    UUID createUserAndGetId(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            UUID roleId,
            boolean withPassword
    );

    String generateOtp(UUID userId);
}
