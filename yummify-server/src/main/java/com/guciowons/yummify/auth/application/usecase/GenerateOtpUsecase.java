package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.service.UserOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GenerateOtpUsecase {
    private final UserOtpService userOtpService;

    public String generate(UUID userId) {
        return userOtpService.set(userId);
    }
}
