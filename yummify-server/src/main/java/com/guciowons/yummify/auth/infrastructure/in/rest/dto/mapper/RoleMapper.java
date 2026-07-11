package com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.RoleManageDto;
import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface RoleMapper {
    @Mapping(target = "id", source = "id.value")
    RoleManageDto toManageDto(Role role);
}
