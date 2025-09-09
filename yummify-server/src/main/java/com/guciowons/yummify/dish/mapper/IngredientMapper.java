package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    @Mapping(target = "name", expression = "java(ingredient.getName().get())")
    IngredientDTO<String> mapToClientDTO(Ingredient ingredient);

    IngredientDTO<TranslatedStringDTO> mapToAdminDTO(Ingredient ingredient);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToEntity(IngredientDTO<TranslatedStringDTO> ingredientDTO);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToUpdateEntity(IngredientDTO<TranslatedStringDTO> ingredientDTO, @MappingTarget Ingredient ingredient);
}
