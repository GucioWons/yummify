package com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientManageDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface IngredientMapper {
    @Mapping(target = "id", source = "id.value")
    IngredientManageDTO mapToManageDTO(Ingredient entity);

    @Mapping(target = "id", source = "id.value")
    IngredientClientDTO mapToClientDTO(Ingredient entity);
}
