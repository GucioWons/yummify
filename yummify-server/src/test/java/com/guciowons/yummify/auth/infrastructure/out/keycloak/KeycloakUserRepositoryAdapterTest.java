package com.guciowons.yummify.auth.infrastructure.out.keycloak;

import com.guciowons.yummify.auth.infrastructure.out.keycloak.adapter.KeycloakUserRepositoryAdapter;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.feign.KeycloakAdminClient;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.mapper.UserRepresentationMapper;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.HashMap;
import java.util.List;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static com.guciowons.yummify.auth.infrastructure.fixture.AuthInfrastructureFixture.givenAdminToken;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class KeycloakUserRepositoryAdapterTest {
    private final KeycloakAuthenticator keycloakAuthenticator = mock(KeycloakAuthenticator.class);
    private final KeycloakAdminClient keycloakAdminClient = mock(KeycloakAdminClient.class);
    private final UserRepresentationMapper userRepresentationMapper = mock(UserRepresentationMapper.class);

    private final KeycloakUserRepositoryAdapter underTest = new KeycloakUserRepositoryAdapter(
            keycloakAuthenticator,
            keycloakAdminClient,
            userRepresentationMapper
    );

    @Test
    void shouldReturnTrue_WhenEmailExists() {
        // given
        var email = givenUserEmail();
        var token = givenAdminToken();

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(keycloakAdminClient.countUsersByEmail(token, email.value())).thenReturn(1);

        // when
        boolean exists = underTest.existsByEmail(email);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(keycloakAdminClient).countUsersByEmail(token, email.value());

        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalse_WhenEmailDoesNotExist() {
        // given
        var email = givenUserEmail();
        var token = givenAdminToken();

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(keycloakAdminClient.countUsersByEmail(token, email.value())).thenReturn(0);

        // when
        boolean exists = underTest.existsByEmail(email);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(keycloakAdminClient).countUsersByEmail(token, email.value());

        assertThat(exists).isFalse();
    }

    @Test
    void shouldReturnTrue_WhenUsernameExists() {
        // given
        var username = givenUserUsername();
        var token = givenAdminToken();

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(keycloakAdminClient.countUsersByUsername(token, username.value())).thenReturn(1);

        // when
        boolean exists = underTest.existsByUsername(username);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(keycloakAdminClient).countUsersByUsername(token, username.value());

        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalse_WhenUsernameDoesNotExist() {
        // given
        var username = givenUserUsername();
        var token = givenAdminToken();

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(keycloakAdminClient.countUsersByUsername(token, username.value())).thenReturn(0);

        // when
        boolean exists = underTest.existsByUsername(username);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(keycloakAdminClient).countUsersByUsername(token, username.value());

        assertThat(exists).isFalse();
    }

    @Test
    void shouldCreateUserWithPassword() {
        // given
        var token = givenAdminToken();
        var user = givenUser(true);
        var representation = mock(UserRepresentation.class);
        var expectedUserId = givenUserExternalId(1);

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(userRepresentationMapper.toUserRepresentation(user)).thenReturn(representation);
        when(keycloakAdminClient.getUserByEmail(token, user.getEmail().value())).thenReturn(List.of(representation));
        when(representation.getId()).thenReturn(expectedUserId.value().toString());

        // when
        var result = underTest.createUser(user);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(userRepresentationMapper).toUserRepresentation(user);
        verify(keycloakAdminClient).createUser(token, representation);
        verify(keycloakAdminClient).getUserByEmail(token, user.getEmail().value());

        assertThat(result).isEqualTo(user);
        assertThat(result.getId()).isEqualTo(expectedUserId);
    }

    @Test
    void shouldUpdateOtp() {
        // given
        var userId = givenUserExternalId(1);
        var otp = givenOtp();
        var representation = new UserRepresentation();
        representation.setAttributes(new HashMap<>());
        var token = givenAdminToken();

        when(keycloakAuthenticator.getAdminToken()).thenReturn(token);
        when(keycloakAdminClient.getUser(userId.value().toString(), token)).thenReturn(representation);

        // when
        underTest.updateOtp(userId, otp);

        // then
        verify(keycloakAuthenticator).getAdminToken();
        verify(keycloakAdminClient).updateUser(userId.value().toString(), token, representation);
        verify(keycloakAdminClient).getUser(userId.value().toString(), token);

        assertThat(representation.getAttributes().get("otp")).contains(otp.password().value());
        assertThat(representation.getAttributes().get("otpExpirationDate")).contains(otp.expiresAt().toString());
    }
}