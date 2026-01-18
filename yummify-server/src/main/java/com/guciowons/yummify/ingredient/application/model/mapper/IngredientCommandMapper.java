package com.guciowons.yummify.ingredient.application.model.mapper;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsCommand;
import com.guciowons.yummify.ingredient.application.model.GetIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IngredientCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateIngredientCommand toCreateIngredientCommand(UUID restaurantId, TranslatedString name);

    @Mapping(target = "id", expression = "java(IngredientId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    UpdateIngredientCommand toUpdateIngredientCommand(UUID id, UUID restaurantId, TranslatedString name);

    @Mapping(target = "id", expression = "java(IngredientId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetIngredientCommand toGetIngredientCommand(UUID id, UUID restaurantId);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetAllIngredientsCommand toGetAllIngredientsCommand(UUID restaurantId);
}
