package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.menu.application.model.CreateMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.GetMenuVersionQuery;
import com.guciowons.yummify.menu.application.model.PublishMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.RestoreMenuVersionCommand;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuVersionCommandMapper {
    CreateMenuVersionCommand toCreateMenuVersionCommand(UUID restaurantId);

    GetMenuVersionQuery toGetMenuVersionQuery(UUID restaurantId);

    PublishMenuVersionCommand toPublishMenuVersionCommand(UUID restaurantId);

    RestoreMenuVersionCommand toRestoreMenuVersionCommand(UUID id, UUID restaurantId);

    default MenuVersion.Id toId(UUID id) {
        return MenuVersion.Id.of(id);
    }

    default MenuVersion.RestaurantId toRestaurantId(UUID id) {
        return MenuVersion.RestaurantId.of(id);
    }
}
