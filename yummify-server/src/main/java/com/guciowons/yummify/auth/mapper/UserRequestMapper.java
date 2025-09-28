package com.guciowons.yummify.auth.mapper;

import com.guciowons.yummify.auth.UserRequestDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    UserRepresentation toUserRepresentation(UserRequestDTO requestDTO);
}
