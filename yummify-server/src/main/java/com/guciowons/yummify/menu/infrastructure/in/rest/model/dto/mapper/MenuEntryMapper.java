package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuEntryMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "price", source = "price.value")
    MenuEntryDto toDto(MenuEntry menuEntry);

    @Mapping(target = "id", expression = "java(MenuEntry.Id.of(dto.id()))")
    @Mapping(target = "dishId", expression = "java(MenuEntry.DishId.of(dto.dishId()))")
    @Mapping(target = "price", expression = "java(MenuEntry.Price.of(dto.price()))")
    MenuEntrySnapshot toSnapshot(MenuEntryDto dto);
}
