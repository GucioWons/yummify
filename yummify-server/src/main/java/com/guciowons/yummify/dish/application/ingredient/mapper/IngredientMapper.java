package com.guciowons.yummify.dish.application.ingredient.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface IngredientMapper {
    IngredientManageDTO mapToManageDTO(Ingredient entity);

    IngredientClientDTO mapToClientDTO(Ingredient entity);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToSaveEntity(IngredientManageDTO ingredientDTO);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToUpdateEntity(IngredientManageDTO ingredientDTO, @MappingTarget Ingredient entity);
}
