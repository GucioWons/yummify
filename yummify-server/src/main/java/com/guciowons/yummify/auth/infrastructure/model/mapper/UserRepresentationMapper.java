package com.guciowons.yummify.auth.infrastructure.model.mapper;

import com.guciowons.yummify.auth.domain.model.UserRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface UserRepresentationMapper {
    @Mapping(target = "enabled", expression = "java(true)")
    UserRepresentation mapToUserRepresentation(UserRequest userRequest);

    @AfterMapping
    default void afterMapping(@MappingTarget UserRepresentation userRepresentation, UserRequest userRequest) {
        userRepresentation.setAttributes(Map.of("restaurantId", List.of(userRequest.restaurantId().toString())));
    }
}
