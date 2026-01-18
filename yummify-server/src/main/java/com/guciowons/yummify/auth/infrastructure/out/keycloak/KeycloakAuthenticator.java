package com.guciowons.yummify.auth.infrastructure.out.keycloak;

import com.guciowons.yummify.auth.infrastructure.framework.KeycloakProperties;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.feign.KeycloakAuthClient;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenRequest;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakAuthenticator {
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakProperties keycloakProperties;

    public String getAdminToken() {
        AdminTokenRequest request = new AdminTokenRequest(
                keycloakProperties.adminUsername(),
                keycloakProperties.adminPassword()
        );

        AdminTokenResponse response = keycloakAuthClient.getAdminAccessToken(request);

        return "Bearer " + response.accessToken();
    }
}
