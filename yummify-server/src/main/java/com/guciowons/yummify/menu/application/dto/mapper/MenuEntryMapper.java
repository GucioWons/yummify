package com.guciowons.yummify.menu.application.dto.mapper;

import com.guciowons.yummify.menu.application.dto.MenuEntryDTO;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MenuEntryMapper {
    MenuEntryDTO mapToDTO(MenuEntry entity);

    @Mapping(target = "id", ignore = true)
    MenuEntry mapToSaveEntity(MenuEntryDTO dto);

    @Mapping(target = "id", ignore = true)
    MenuEntry mapToUpdateEntity(MenuEntryDTO dto, @MappingTarget MenuEntry entity);
}
