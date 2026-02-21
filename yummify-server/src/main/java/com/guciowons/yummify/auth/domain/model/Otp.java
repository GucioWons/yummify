package com.guciowons.yummify.auth.domain.model;

import java.time.LocalDateTime;

public record Otp(Password password, LocalDateTime expiresAt) {
    public static Otp of(Password password, LocalDateTime expiresAt) {
        return new Otp(password, expiresAt);
    }
}
