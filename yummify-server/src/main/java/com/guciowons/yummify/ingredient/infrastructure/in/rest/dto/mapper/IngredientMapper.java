package com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientClientDto;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientManageDto;
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
    IngredientManageDto mapToManageDto(Ingredient entity);

    @Mapping(target = "id", source = "id.value")
    IngredientClientDto mapToClientDto(Ingredient entity);
}
