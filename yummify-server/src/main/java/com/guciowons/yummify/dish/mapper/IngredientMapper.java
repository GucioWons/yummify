package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientDTO mapToDTO(Ingredient entity);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToEntity(IngredientDTO dto);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToUpdateEntity(IngredientDTO dto, @MappingTarget Ingredient entity);
}
