package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.menu.application.model.CreateMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.GetMenuVersionQuery;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuVersionCommandMapper {
    CreateMenuVersionCommand toCreateMenuVersionCommand(UUID restaurantId);

    GetMenuVersionQuery toGetMenuVersionQuery(UUID restaurantId);

    default MenuVersion.RestaurantId toRestaurantId(UUID id) {
        return MenuVersion.RestaurantId.of(id);
    }
}
