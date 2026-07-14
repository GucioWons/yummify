package com.guciowons.yummify.auth.infrastructure.out.keycloak.model.mapper;

import com.guciowons.yummify.common.security.domain.Permission;
import org.keycloak.representations.idm.RoleRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleRepresentationMapper {
    List<RoleRepresentation> toRoleRepresentations(Set<Permission> permissions);

    @Mapping(target = "name", source = "permission")
    @Mapping(target = "id", source = "externalId")
    RoleRepresentation toRoleRepresentation(Permission permission);
}
