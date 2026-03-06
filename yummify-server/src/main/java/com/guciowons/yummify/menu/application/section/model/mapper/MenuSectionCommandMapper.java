package com.guciowons.yummify.menu.application.section.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.application.section.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionNameCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionPositionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface MenuSectionCommandMapper {
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    CreateMenuSectionCommand toCreateMenuSectionCommand(UUID restaurantId, Map<String, String> name);

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
