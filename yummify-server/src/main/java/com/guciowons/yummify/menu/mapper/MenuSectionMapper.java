package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.menu.MenuSectionDTO;
import com.guciowons.yummify.menu.entity.MenuSection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MenuSectionMapper {
    MenuSectionDTO mapToDTO(MenuSection entity);

    MenuSection mapToEntity(MenuSectionDTO dto);

    MenuSection mapToUpdateEntity(MenuSectionDTO dto, @MappingTarget MenuSection entity);
}
