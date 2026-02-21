package com.guciowons.yummify.ingredient.application.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsQuery;
import com.guciowons.yummify.ingredient.application.model.GetIngredientQuery;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
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
public interface IngredientCommandMapper {
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    CreateIngredientCommand toCreateIngredientCommand(UUID restaurantId, Map<String, String> name);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    UpdateIngredientCommand toUpdateIngredientCommand(UUID id, UUID restaurantId, Map<String, String> name);

    GetIngredientQuery toGetIngredientQuery(UUID id, UUID restaurantId);

    GetAllIngredientsQuery toGetAllIngredientsQuery(UUID restaurantId);

    default Ingredient.Id toId(UUID id) {
        return new Ingredient.Id(id);
    }

    default Ingredient.RestaurantId toRestaurantId(UUID id) {
        return new Ingredient.RestaurantId(id);
    }
}
