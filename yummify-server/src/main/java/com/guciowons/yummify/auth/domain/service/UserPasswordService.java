package com.guciowons.yummify.auth.domain.service;

import com.guciowons.yummify.auth.domain.port.UserManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPasswordService {
    private final SecurePasswordGenerator securePasswordGenerator;
    private final UserManagementPort userManagementPort;

    public void set(UUID userId) {
        String password = securePasswordGenerator.generate(16);
        userManagementPort.updatePassword(userId, password);
    }
}
