package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionEntriesCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionNameCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionPositionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface MenuSectionCommandMapper {
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    CreateMenuSectionCommand toCreateMenuSectionCommand(UUID restaurantId, Map<String, String> name);

    UpdateMenuSectionEntriesCommand toUpdateMenuSectionEntriesCommand(
            UUID id,
            UUID restaurantId,
            List<MenuEntrySnapshot> entrySnapshots
    );

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    UpdateMenuSectionNameCommand toUpdateMenuSectionNameCommand(UUID id, UUID restaurantId, Map<String, String> name);

    UpdateMenuSectionPositionCommand toUpdateMenuSectionPositionCommand(UUID id, UUID restaurantId, Integer position);

    default MenuVersion.RestaurantId toRestaurantId(UUID id) {
        return MenuVersion.RestaurantId.of(id);
    }

    default MenuSection.Id toMenuSectionId(UUID id) {
        return MenuSection.Id.of(id);
    }
}
