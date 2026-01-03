package com.guciowons.yummify.auth.domain.port;

import com.guciowons.yummify.auth.domain.model.UserRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserManagementPort {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void updatePassword(UUID userId, String password);

    UUID createUser(UserRequest userRequest);

    void updateOtp(UUID userId, String otp, LocalDateTime otpExpirationDate);
}
