package com.guciowons.yummify.auth.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.JpaRole;
import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = JpaTranslatedStringMapper.class
)
public interface JpaRoleMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    JpaRole toJpa(Role role);

    Role toDomain(JpaRole jpaRole);

    default Role.Id toId(UUID id) {
        return Role.Id.of(id);
    }

    default Role.RestaurantId toRestaurantId(UUID id) {
        return Role.RestaurantId.of(id);
    }
}
