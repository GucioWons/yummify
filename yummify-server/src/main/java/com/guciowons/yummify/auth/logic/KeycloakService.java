package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.UserResponseDTO;
import com.guciowons.yummify.auth.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService implements PublicAuthService {
    private static final String GRANT_TYPE = "password";
    private static final String CLIENT_ID = "admin-cli";

    @Value("${spring.security.oauth2.admin.username}")
    private String adminUsername;

    @Value("${spring.security.oauth2.admin.password}")
    private String adminPassword;

    private final SecurePasswordGenerator securePasswordGenerator;
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakAdminClient keycloakAdminClient;

    @Override
    public UUID createUserAndGetId(UserRequestDTO userRequest) {
        String adminToken = "Bearer " + getAdminToken().access_token();

        if (keycloakAdminClient.countUsersByEmail(adminToken, userRequest.getEmail()) > 0) {
            throw new IllegalArgumentException();
        }

        if (keycloakAdminClient.countUsersByUsername(adminToken, userRequest.getUsername()) > 0) {
            throw new IllegalArgumentException();
        }

        keycloakAdminClient.createUser(adminToken, userRequest);

        UserResponseDTO userResponse = keycloakAdminClient.getUserByEmail(adminToken, userRequest.getEmail()).getFirst();
        String password = securePasswordGenerator.generate(16);
        keycloakAdminClient.setPassword(userResponse.id().toString(), adminToken, new PasswordRequestDTO(password));

        return userResponse.id();
    }

    private AdminTokenResponseDTO getAdminToken() {
        return keycloakAuthClient.getAdminAccessToken(
                new AdminTokenRequestDTO(GRANT_TYPE, CLIENT_ID, adminUsername, adminPassword)
        );
    }
}
