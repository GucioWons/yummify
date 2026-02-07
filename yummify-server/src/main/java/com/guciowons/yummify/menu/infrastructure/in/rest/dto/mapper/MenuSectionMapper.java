package com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuSectionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuSectionManageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        TranslatedStringMapper.class,
        MenuEntryMapper.class
})
public interface MenuSectionMapper {
    @Mapping(target = "id", source = "id.value")
    MenuSectionManageDto toManageDto(MenuSection menuSection);

    @Mapping(target = "id", source = "id.value")
    MenuSectionClientDto toClientDto(MenuSection menuSection);
}
