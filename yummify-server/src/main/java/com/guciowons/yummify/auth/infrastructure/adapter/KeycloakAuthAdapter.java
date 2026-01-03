package com.guciowons.yummify.auth.infrastructure.adapter;

import com.guciowons.yummify.auth.infrastructure.feign.KeycloakAuthClient;
import com.guciowons.yummify.auth.infrastructure.model.AdminTokenRequest;
import com.guciowons.yummify.auth.infrastructure.model.AdminTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakAuthAdapter {
    private final KeycloakAuthClient keycloakAuthClient;

    @Value("${spring.security.oauth2.admin.username}")
    private String adminUsername;

    @Value("${spring.security.oauth2.admin.password}")
    private String adminPassword;

    public String getAdminToken() {
        AdminTokenRequest request = new AdminTokenRequest(adminUsername, adminPassword);

        AdminTokenResponse response = keycloakAuthClient.getAdminAccessToken(request);

        return "Bearer " + response.access_token();
    }
}
