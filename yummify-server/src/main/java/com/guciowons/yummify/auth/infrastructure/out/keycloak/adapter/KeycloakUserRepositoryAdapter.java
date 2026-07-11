package com.guciowons.yummify.auth.infrastructure.out.keycloak.adapter;

import com.guciowons.yummify.auth.application.service.RoleLookupService;
import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.KeycloakAuthenticator;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.feign.KeycloakAdminClient;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.mapper.UserRepresentationMapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KeycloakUserRepositoryAdapter implements UserRepository {
    private final KeycloakAuthenticator keycloakAuthenticator;
    private final KeycloakAdminClient keycloakAdminClient;
    private final UserRepresentationMapper userRepresentationMapper;
    private final RoleLookupService roleLookupService;

    @Override
    public boolean existsByEmail(User.Email email) {
        return keycloakAdminClient.countUsersByEmail(getAdminToken(), email.value()) > 0;
    }

    @Override
    public boolean existsByUsername(User.Username username) {
        return keycloakAdminClient.countUsersByUsername(getAdminToken(), username.value()) > 0;
    }

    @Override
    public User createUser(User user) {
        String adminToken = getAdminToken();

        keycloakAdminClient.createUser(adminToken, userRepresentationMapper.toUserRepresentation(user));

        UserRepresentation userRepresentation = keycloakAdminClient.getUserByEmail(adminToken, user.getEmail().value())
                .getFirst();

        user.assignId(User.ExternalId.of(userRepresentation.getId()));

        return user;
    }

    @Override
    public void updateOtp(User.ExternalId userId, Otp otp) {
        String adminToken = keycloakAuthenticator.getAdminToken();

        UserRepresentation userResponse = keycloakAdminClient.getUser(userId.value().toString(), adminToken);

        userResponse.getAttributes().put("otp", Collections.singletonList(otp.password().value()));
        userResponse.getAttributes().put("otpExpirationDate", Collections.singletonList(otp.expiresAt().toString()));
        keycloakAdminClient.updateUser(userId.value().toString(), adminToken, userResponse);
    }

    @Override
    public List<User> getAllUsersByRestaurantId(User.RestaurantId restaurantId) {
        String customAttributesQueryParam = "restaurantId:%s".formatted(restaurantId.value());

        return keycloakAdminClient.getUsersByRestaurantId(getAdminToken(), customAttributesQueryParam).stream()
                .map(userRepresentation -> toUserWithRole(userRepresentation, restaurantId))
                .toList();
    }

    private String getAdminToken() {
        return keycloakAuthenticator.getAdminToken();
    }

    private User toUserWithRole(UserRepresentation userRepresentation, User.RestaurantId restaurantId) {
        UUID roleId = userRepresentationMapper.extractUuidAttribute(userRepresentation, "roleId");

        Role role = roleLookupService.getByIdAndRestaurantId(
                Role.Id.of(roleId),
                Role.RestaurantId.of(restaurantId.value()));

        return userRepresentationMapper.toUser(userRepresentation, role);
    }
}
