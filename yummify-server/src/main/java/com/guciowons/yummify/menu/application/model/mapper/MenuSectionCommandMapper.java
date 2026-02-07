package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionCommand;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

public interface MenuSectionCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateMenuSectionCommand toCreateMenuSectionCommand(UUID restaurantId, TranslatedString name, Integer position);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    UpdateMenuSectionCommand toUpdateMenuSectionCommand(
            UUID restaurantId,
            TranslatedString name,
            Integer position,
            List<MenuEntrySnapshot> menuEntrySnapshots
    );
}
