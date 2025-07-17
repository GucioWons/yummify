package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class AbstractKeycloakService {
    private static final String BEARER_PREFIX = "Bearer ";

    @Value("${spring.security.oauth2.admin.username}")
    private String adminUsername;

    @Value("${spring.security.oauth2.admin.password}")
    private String adminPassword;

    protected final KeycloakAuthClient keycloakAuthClient;
    protected final KeycloakAdminClient keycloakAdminClient;
    protected final SecurePasswordGenerator securePasswordGenerator;

    public String getAdminToken() {
        AdminTokenRequestDTO request = new AdminTokenRequestDTO(adminUsername, adminPassword);

        AdminTokenResponseDTO response = keycloakAuthClient.getAdminAccessToken(request);

        return BEARER_PREFIX + response.access_token();
    }
}
