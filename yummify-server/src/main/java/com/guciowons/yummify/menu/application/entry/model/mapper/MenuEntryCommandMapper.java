package com.guciowons.yummify.menu.application.entry.model.mapper;

import com.guciowons.yummify.menu.application.entry.model.CreateMenuEntryCommand;
import com.guciowons.yummify.menu.application.entry.model.UpdateMenuEntryCommand;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuEntryCommandMapper {
    CreateMenuEntryCommand toCreateMenuEntryCommand(UUID sectionId, UUID restaurantId, UUID dishId, BigDecimal price);
    UpdateMenuEntryCommand toUpdateMenuEntryCommand(UUID sectionId, UUID id, UUID restaurantId, UUID dishId, BigDecimal price);

    default MenuSection.Id toSectionId(UUID sectionId) {
        return MenuSection.Id.of(sectionId);
    }

    default MenuVersion.RestaurantId toRestaurantId(UUID restaurantId) {
        return MenuVersion.RestaurantId.of(restaurantId);
    }

    default MenuEntry.Id toMenuEntryId(UUID id) {
        return MenuEntry.Id.of(id);
    }

    default MenuEntry.DishId toDishId(UUID dishId) {
        return MenuEntry.DishId.of(dishId);
    }

    default MenuEntry.Price toPrice(BigDecimal price) {
        return MenuEntry.Price.of(price);
    }
}
