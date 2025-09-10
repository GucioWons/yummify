package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.temp.mapper.TranslatableMapper;
import com.guciowons.yummify.dish.IngredientClientDTO;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.IngredientManageDTO;
import com.guciowons.yummify.dish.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface IngredientMapper extends TranslatableMapper<Ingredient, IngredientDTO, IngredientManageDTO, IngredientClientDTO> {
    IngredientManageDTO mapToManageDTO(Ingredient entity);

    IngredientClientDTO mapToClientDTO(Ingredient entity);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToSaveEntity(IngredientManageDTO ingredientDTO);

    @Mapping(target = "id", ignore = true)
    Ingredient mapToUpdateEntity(IngredientManageDTO ingredientDTO, @MappingTarget Ingredient entity);
}
