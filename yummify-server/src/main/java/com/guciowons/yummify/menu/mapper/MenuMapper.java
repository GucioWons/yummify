package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.menu.MenuDTO;
import com.guciowons.yummify.menu.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = MenuSectionMapper.class)
public interface MenuMapper {
    MenuDTO mapToDTO(Menu entity);

    @Mapping(target = "id", ignore = true)
    Menu mapToEntity(MenuDTO dto);

    @Mapping(target = "id", ignore = true)
    Menu mapToUpdateEntity(MenuDTO dto, @MappingTarget Menu target);
}
