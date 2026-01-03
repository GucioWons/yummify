package com.guciowons.yummify.auth.application.dto.mapper;

import com.guciowons.yummify.auth.application.dto.UserDTO;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToDTO(UserPrincipal userPrincipal);
}
