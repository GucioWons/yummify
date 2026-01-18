package com.guciowons.yummify.auth.infrastructure.fixture;

import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenRequest;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenResponse;

public class AuthInfrastructureFixture {
    public static AdminTokenRequest givenAdminTokenRequest() {
        return new AdminTokenRequest(givenKeycloakAdminUsername(), givenKeycloakAdminPassword());
    }

    public static String givenKeycloakAdminUsername() {
        return "adminUsername";
    }

    public static String givenKeycloakAdminPassword() {
        return "adminPassword";
    }

    public static AdminTokenResponse givenAdminTokenResponse() {
        return new AdminTokenResponse(givenAdminToken());
    }

    public static String givenAdminToken() {
        return "adminToken";
    }
}
