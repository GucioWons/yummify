package com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.auth.infrastructure.in.rest.dto.UserDTO;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToDTO(UserPrincipal userPrincipal);
}
