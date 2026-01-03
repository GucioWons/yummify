package com.guciowons.yummify.auth.domain.service;

import com.guciowons.yummify.auth.domain.model.UserRequest;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.domain.port.UserManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserManagementPort userManagementPort;

    public UUID create(UserRequest request) {
        if (userManagementPort.existsByEmail(request.email())) {
            throw new AccountExistsByEmailException();
        }

        if (userManagementPort.existsByUsername(request.username())) {
            throw new AccountExistsByUsernameException();
        }

        return userManagementPort.createUser(request);
    }
}
