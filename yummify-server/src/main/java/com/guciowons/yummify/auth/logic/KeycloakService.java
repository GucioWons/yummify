package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.client.AdminTokenRequestDTO;
import com.guciowons.yummify.auth.client.KeycloakAdminClient;
import com.guciowons.yummify.auth.client.KeycloakAuthClient;
import com.guciowons.yummify.auth.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService implements PublicAuthService {
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakAdminClient keycloakAdminClient;

    @Override
    public UUID createUserAndGetId(UserRequestDTO userRequest) {
        String adminToken = "Bearer " + getAdminToken();

        if (keycloakAdminClient.countUsersByEmail(adminToken, userRequest.getEmail()) > 0) {
            throw new IllegalArgumentException();
        }

        keycloakAdminClient.createUser(adminToken, userRequest);

        return keycloakAdminClient.getUserByEmail(adminToken, userRequest.getEmail()).getFirst().id();
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
