package com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.UserManageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "firstName", source = "personalData.firstName")
    @Mapping(target = "lastName", source = "personalData.lastName")
    @Mapping(target = "roleId", source = "role.id.value")
    UserManageDto toManageDto(User user);
}
