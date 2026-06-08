package com.guciowons.yummify.auth.infrastructure.out.keycloak.model.mapper;

import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.model.Password;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserRepresentationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "firstName", source = "personalData.firstName")
    @Mapping(target = "lastName", source = "personalData.lastName")
    @Mapping(target = "enabled", expression = "java(true)")
    @Mapping(target = "credentials", source = "password")
    UserRepresentation toUserRepresentation(User user);

    default List<CredentialRepresentation> toCredentials(Password password) {
        CredentialRepresentation credentialRepresentation = toCredentialRepresentation(password);
        return List.of(credentialRepresentation);
    }

    @Mapping(target = "temporary", expression = "java(true)")
    @Mapping(target = "type", expression = "java(CredentialRepresentation.PASSWORD)")
    CredentialRepresentation toCredentialRepresentation(Password password);

    @AfterMapping
    default void afterMapping(@MappingTarget UserRepresentation userRepresentation, User user) {
        userRepresentation.setAttributes(Map.of("restaurantId", List.of(user.getRestaurantId().value().toString())));
    }

    default User toUser(UserRepresentation userRepresentation) {
        UUID restaurantId = userRepresentation.getAttributes()
                .getOrDefault("restaurantId", List.of())
                .stream()
                .findFirst()
                .map(UUID::fromString)
                .orElseThrow();

        User user = User.create(
                User.RestaurantId.of(restaurantId),
                User.Email.of(userRepresentation.getEmail()),
                User.Username.of(userRepresentation.getUsername()),
                User.PersonalData.of(
                        userRepresentation.getFirstName(),
                        userRepresentation.getLastName()
                ),
                null
        );

        user.assignId(User.ExternalId.of(userRepresentation.getId()));

        return user;
    }
}
