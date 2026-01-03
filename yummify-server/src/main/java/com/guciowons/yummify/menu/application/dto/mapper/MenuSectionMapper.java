package com.guciowons.yummify.menu.application.dto.mapper;

import com.guciowons.yummify.common.i8n.application.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.application.dto.MenuSectionClientDTO;
import com.guciowons.yummify.menu.application.dto.MenuSectionManageDTO;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuEntryMapper.class
        }
)
public interface MenuSectionMapper {
    MenuSectionManageDTO mapToManageDTO(MenuSection entity);

    MenuSectionClientDTO mapToClientDTO(MenuSection entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entries", ignore = true)
    MenuSection mapToSaveEntity(MenuSectionManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entries", ignore = true)
    MenuSection mapToUpdateEntity(MenuSectionManageDTO dto, @MappingTarget MenuSection entity);
}
