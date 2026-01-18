package com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.menu.domain.entity.update.MenuEntryData;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuEntryDto;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuEntryMapper {
    MenuEntryDto toDTO(MenuEntry entity);

    MenuEntryData toEntity(MenuEntryDto dto);
}
