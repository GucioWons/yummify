package com.guciowons.yummify.common.security.application.mapper;

import com.guciowons.yummify.common.security.domain.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(String permission);
}
