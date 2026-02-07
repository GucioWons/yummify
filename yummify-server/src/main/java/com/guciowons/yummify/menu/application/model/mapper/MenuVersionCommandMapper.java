package com.guciowons.yummify.menu.application.model.mapper;

import com.guciowons.yummify.menu.application.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MenuVersionCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateMenuVersionCommand toCreateMenuVersionCommand(UUID restaurantId);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetAllMenuVersionsCommand toGetAllMenuVersionsCommand(UUID restaurantId);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetDraftMenuVersionCommand toGetDraftMenuVersionCommand(UUID restaurantId);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetPublishedMenuVersionCommand toGetPublishedMenuVersionCommand(UUID restaurantId);
}
