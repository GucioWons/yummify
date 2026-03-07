package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuEntryMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "price", source = "price.value")
    MenuEntryDto toDto(MenuEntry menuEntry);

    default MenuEntry.Id toId(UUID id) {
        return id != null ? MenuEntry.Id.of(id) : null;
    }
}
