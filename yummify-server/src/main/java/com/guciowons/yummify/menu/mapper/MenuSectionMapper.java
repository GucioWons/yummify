package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.menu.dto.section.MenuSectionClientDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.MenuSection;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuEntryMapper.class
        }
)
public interface MenuSectionMapper extends TranslatableMapper<MenuSection, MenuSectionManageDTO, MenuSectionClientDTO, BaseEntityDTO> {
    MenuSectionManageDTO mapToManageDTO(MenuSection entity);

    MenuSectionClientDTO mapToClientDTO(MenuSection entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entries", ignore = true)
    MenuSection mapToSaveEntity(MenuSectionManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entries", ignore = true)
    MenuSection mapToUpdateEntity(MenuSectionManageDTO dto, @MappingTarget MenuSection entity);

    default BaseEntityDTO mapToListDTO(MenuSection entity) {
        throw new UnsupportedOperationException();
    }
}
