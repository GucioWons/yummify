package com.guciowons.yummify.auth.domain.service;

import com.guciowons.yummify.auth.domain.port.UserManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserOtpService {
    private final SecurePasswordGenerator securePasswordGenerator;
    private final UserManagementPort userManagementPort;

    public String set(UUID userId) {
        String otp = securePasswordGenerator.generate(16);
        LocalDateTime otpExpirationDate = LocalDateTime.now().plusMinutes(5);
        userManagementPort.updateOtp(userId, otp, otpExpirationDate);
        return otp;
    }
}
