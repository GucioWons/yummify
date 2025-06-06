package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.UserResponseDTO;
import com.guciowons.yummify.auth.client.AdminTokenRequestDTO;
import com.guciowons.yummify.auth.client.KeycloakAdminClient;
import com.guciowons.yummify.auth.client.KeycloakAuthClient;
import com.guciowons.yummify.auth.client.PasswordRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService implements PublicAuthService {
    private final SecurePasswordGenerator securePasswordGenerator;
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakAdminClient keycloakAdminClient;

    @Override
    public UUID createUserAndGetId(UserRequestDTO userRequest) {
        String adminToken = "Bearer " + getAdminToken();

        if (keycloakAdminClient.countUsersByEmail(adminToken, userRequest.getEmail()) > 0) {
            throw new IllegalArgumentException();
        }

        keycloakAdminClient.createUser(adminToken, userRequest);

        UserResponseDTO userResponse = keycloakAdminClient.getUserByEmail(adminToken, userRequest.getEmail()).getFirst();
        String password = securePasswordGenerator.generate(16);
        keycloakAdminClient.setPassword(userResponse.id().toString(), adminToken, new PasswordRequestDTO(password));

        return userResponse.id();
    }

    private String getAdminToken() {
        return keycloakAuthClient.getAdminAccessToken(new AdminTokenRequestDTO(
                "password",
                "admin-cli",
                "admin",
                "admin"
        )).access_token();
    }
}
