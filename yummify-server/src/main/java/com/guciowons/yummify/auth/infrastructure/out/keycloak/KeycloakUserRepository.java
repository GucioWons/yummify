package com.guciowons.yummify.auth.infrastructure.out.keycloak;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.model.value.Email;
import com.guciowons.yummify.auth.domain.model.value.UserId;
import com.guciowons.yummify.auth.domain.model.value.Username;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.mapper.UserRepresentationMapper;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.feign.KeycloakAdminClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class KeycloakUserRepository implements UserRepository {
    private final KeycloakAuthenticator keycloakAuthenticator;
    private final KeycloakAdminClient keycloakAdminClient;
    private final UserRepresentationMapper userRepresentationMapper;

    @Override
    public boolean existsByEmail(Email email) {
        return keycloakAdminClient.countUsersByEmail(getAdminToken(), email.value()) > 0;
    }

    @Override
    public boolean existsByUsername(Username username) {
        return keycloakAdminClient.countUsersByUsername(getAdminToken(), username.value()) > 0;
    }

    @Override
    public UserId createUser(User user) {
        String adminToken = getAdminToken();

        keycloakAdminClient.createUser(adminToken, userRepresentationMapper.toUserRepresentation(user));

        UserRepresentation userRepresentation = keycloakAdminClient.getUserByEmail(adminToken, user.getEmail().value())
                .getFirst();

        return UserId.of(userRepresentation.getId());
    }

    @Override
    public void updateOtp(UserId userId, Otp otp) {
        String adminToken = keycloakAuthenticator.getAdminToken();

        UserRepresentation userResponse = keycloakAdminClient.getUser(userId.value().toString(), adminToken);

        userResponse.getAttributes().put("otp", Collections.singletonList(otp.value()));
        userResponse.getAttributes().put("otpExpirationDate", Collections.singletonList(otp.expiresAt().toString()));
        keycloakAdminClient.updateUser(userId.value().toString(), adminToken, userResponse);
    }

    private String getAdminToken() {
        return keycloakAuthenticator.getAdminToken();
    }
}
