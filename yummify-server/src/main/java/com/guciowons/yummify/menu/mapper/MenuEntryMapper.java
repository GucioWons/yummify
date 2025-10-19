package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.common.core.mapper.BaseEntityMapper;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.entity.MenuEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MenuEntryMapper extends BaseEntityMapper<MenuEntryDTO, MenuEntry> {
    @Mapping(target = "dish.id", source = "dishId")
    MenuEntryDTO mapToDTO(MenuEntry entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishId", source = "dish.id")
    MenuEntry mapToSaveEntity(MenuEntryDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishId", source = "dish.id")
    MenuEntry mapToUpdateEntity(MenuEntryDTO dto, @MappingTarget MenuEntry entity);
}
