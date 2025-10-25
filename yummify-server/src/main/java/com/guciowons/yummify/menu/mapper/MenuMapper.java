package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuListDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuSectionMapper.class
        }
)
public interface MenuMapper extends TranslatableMapper<Menu, MenuManageDTO, MenuClientDTO, MenuListDTO> {
    MenuManageDTO mapToManageDTO(Menu entity);

    MenuClientDTO mapToClientDTO(Menu entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "active", ignore = true)
    Menu mapToSaveEntity(MenuManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "active", ignore = true)
    Menu mapToUpdateEntity(MenuManageDTO dto, @MappingTarget Menu entity);
}
