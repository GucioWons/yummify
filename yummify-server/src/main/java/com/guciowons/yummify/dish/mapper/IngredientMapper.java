package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    @Mapping(target = "description", expression = "java(entity.getDescription().get())")
    IngredientDTO<String> mapToClientDTO(Ingredient ingredient);

    IngredientDTO<TranslatedStringDTO> mapToAdminDTO(Ingredient ingredient);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToEntity(IngredientDTO<TranslatedStringDTO> ingredientDTO);
}
