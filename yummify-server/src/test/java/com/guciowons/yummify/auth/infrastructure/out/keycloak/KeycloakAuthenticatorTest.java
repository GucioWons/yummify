package com.guciowons.yummify.auth.infrastructure.out.keycloak;

import com.guciowons.yummify.auth.infrastructure.framework.KeycloakProperties;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.feign.KeycloakAuthClient;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.infrastructure.fixture.AuthInfrastructureFixture.*;
import static org.mockito.Mockito.*;

class KeycloakAuthenticatorTest {
    private final KeycloakAuthClient keycloakAuthClient = mock(KeycloakAuthClient.class);
    private final KeycloakProperties keycloakProperties = mock(KeycloakProperties.class);

    private final KeycloakAuthenticator underTest = new KeycloakAuthenticator(keycloakAuthClient, keycloakProperties);

    @Test
    void shouldReturnAuthToken() {
        // given
        var adminUsername = givenKeycloakAdminUsername();
        var adminPassword = givenKeycloakAdminPassword();
        var adminTokenRequest = givenAdminTokenRequest();
        var adminTokenResponse = givenAdminTokenResponse();

        when(keycloakProperties.adminUsername()).thenReturn(adminUsername);
        when(keycloakProperties.adminPassword()).thenReturn(adminPassword);
        when(keycloakAuthClient.getAdminAccessToken(adminTokenRequest)).thenReturn(adminTokenResponse);

        // when
        var result = underTest.getAdminToken();

        // then
        verify(keycloakProperties).adminUsername();
        verify(keycloakProperties).adminPassword();
        verify(keycloakAuthClient).getAdminAccessToken(adminTokenRequest);
    }
}