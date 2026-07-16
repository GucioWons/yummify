package com.guciowons.yummify.auth.application.model.mapper;

import com.guciowons.yummify.auth.application.model.CreateRoleCommand;
import com.guciowons.yummify.auth.application.model.GetAllRolesQuery;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.common.security.application.mapper.PermissionMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TranslatedStringMapper.class, PermissionMapper.class}
)
public interface RoleCommandMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "permissions", source = "permissions")
    CreateRoleCommand toCreateRoleCommand(UUID restaurantId, Map<String, String> name, Set<String> permissions);

    GetAllRolesQuery toGetAllRolesQuery(UUID restaurantId);

    default Role.Id toRoleId(UUID id) {
        return Role.Id.of(id);
    }

    default Role.RestaurantId toRestaurantId(UUID id) {
        return Role.RestaurantId.of(id);
    }
}
