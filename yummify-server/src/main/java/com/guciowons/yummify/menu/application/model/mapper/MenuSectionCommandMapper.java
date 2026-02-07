package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionCommand;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface MenuSectionCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateMenuSectionCommand toCreateMenuSectionCommand(UUID restaurantId, Map<String, String> name, Integer position);

    @Mapping(target = "id", expression = "java(Menu.Id.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    UpdateMenuSectionCommand toUpdateMenuSectionCommand(
            UUID id,
            UUID restaurantId,
            Map<String, String> name,
            Integer position,
            List<MenuEntrySnapshot> menuEntrySnapshots
    );
}
