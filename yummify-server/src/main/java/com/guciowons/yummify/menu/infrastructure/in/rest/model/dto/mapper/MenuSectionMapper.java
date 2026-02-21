package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuSectionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuSectionManageDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuEntryMapper.class
        }
)
public interface MenuSectionMapper {
    @Mapping(target = "id", source = "id.value")
    MenuSectionManageDto toManageDto(MenuSection menuSection);

    @Mapping(target = "id", source = "id.value")
    MenuSectionClientDto toClientDto(MenuSection menuSection);
}
