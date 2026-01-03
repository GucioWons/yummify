package com.guciowons.yummify.auth.infrastructure.adapter;

import com.guciowons.yummify.auth.domain.model.UserRequest;
import com.guciowons.yummify.auth.infrastructure.model.mapper.UserRepresentationMapper;
import com.guciowons.yummify.auth.domain.port.UserManagementPort;
import com.guciowons.yummify.auth.infrastructure.feign.KeycloakAdminClient;
import com.guciowons.yummify.auth.infrastructure.model.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KeycloakAdminAdapter implements UserManagementPort {
    private final KeycloakAuthAdapter keycloakAuthAdapter;
    private final KeycloakAdminClient keycloakAdminClient;
    private final UserRepresentationMapper userRepresentationMapper;

    @Override
    public boolean existsByEmail(String email) {
        return keycloakAdminClient.countUsersByEmail(getAdminToken(), email) > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        return keycloakAdminClient.countUsersByUsername(getAdminToken(), username) > 0;
    }

    @Override
    public UUID createUser(UserRequest userRequest) {
        keycloakAdminClient.createUser(getAdminToken(), userRepresentationMapper.mapToUserRepresentation(userRequest));

        UserRepresentation user = keycloakAdminClient.getUserByEmail(getAdminToken(), userRequest.email()).getFirst();

        return UUID.fromString(user.getId());
    }

    @Override
    public void updatePassword(UUID userId, String password) {
        keycloakAdminClient.setPassword(userId.toString(), getAdminToken(), new PasswordRequest(password));
    }

    @Override
    public void updateOtp(UUID userId, String otp, LocalDateTime otpExpirationDate) {
        UserRepresentation userResponse = keycloakAdminClient.getUser(userId.toString(), getAdminToken());

        userResponse.getAttributes().put("otp", Collections.singletonList(otp));
        userResponse.getAttributes().put("otpExpirationDate", Collections.singletonList(otpExpirationDate.toString()));
        keycloakAdminClient.updateUser(userId.toString(), getAdminToken(), userResponse);
    }

    private String getAdminToken() {
        return keycloakAuthAdapter.getAdminToken();
    }
}
