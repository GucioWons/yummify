package com.guciowons.yummify.restaurant.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    private final KeycloakAuthClient keycloakAuthClient;

    public String getAdminToken() {
        return keycloakAuthClient.getAdminAccessToken(new AdminTokenRequestDTO(
                "password",
                "admin-cli",
                "admin",
                "admin"
        )).access_token();
    }
}
