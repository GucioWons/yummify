package com.guciowons.yummify.dish.application.model.mapper;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.application.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DishCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    @Mapping(target = "ingredientIds", expression = "java(DishIngredientIds.of(ingredientIds))")
    CreateDishCommand toCreateDishCommand(
            UUID restaurantId,
            TranslatedString name,
            TranslatedString description,
            List<UUID> ingredientIds
    );

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetAllDishesCommand toGetAllDishesCommand(UUID restaurantId);

    @Mapping(target = "id", expression = "java(DishId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetDishCommand toGetDishCommand(UUID id, UUID restaurantId);

    @Mapping(target = "id", expression = "java(DishId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    @Mapping(target = "ingredientIds", expression = "java(DishIngredientIds.of(ingredientIds))")
    UpdateDishCommand toUpdateDishCommand(
            UUID id,
            UUID restaurantId,
            TranslatedString name,
            TranslatedString description,
            List<UUID> ingredientIds
    );

    @Mapping(target = "id", expression = "java(DishId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    UpdateDishImageCommand toUpdateDishImageCommand(UUID id, MultipartFile image, UUID restaurantId);
}
