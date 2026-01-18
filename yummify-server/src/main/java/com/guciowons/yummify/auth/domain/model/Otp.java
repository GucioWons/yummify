package com.guciowons.yummify.auth.domain.model;

import java.time.LocalDateTime;

public record Otp(String value, LocalDateTime expiresAt) {
    public static Otp of(String value, LocalDateTime expiresAt) {
        return new Otp(value, expiresAt);
    }
}
