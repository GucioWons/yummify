package com.guciowons.yummify.restaurant.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakAdminClient keycloakAdminClient;

    public UUID createUserAndGetId(String email) {
        String adminToken = "Bearer " + getAdminToken();

        if (keycloakAdminClient.countUsersByEmail(email, adminToken) > 0) {
            throw new IllegalArgumentException();
        }


        return null;
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
