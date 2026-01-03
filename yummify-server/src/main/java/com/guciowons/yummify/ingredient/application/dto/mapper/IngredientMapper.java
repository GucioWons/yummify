package com.guciowons.yummify.ingredient.application.dto.mapper;

import com.guciowons.yummify.common.i8n.application.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.ingredient.application.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface IngredientMapper {
    IngredientManageDTO mapToManageDTO(Ingredient entity);

    IngredientClientDTO mapToClientDTO(Ingredient entity);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToSaveEntity(IngredientManageDTO ingredientDTO, @Context UUID restaurantId);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToUpdateEntity(IngredientManageDTO ingredientDTO, @MappingTarget Ingredient entity);
}
