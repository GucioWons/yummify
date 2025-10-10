package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.dish.dto.DishClientDTO;
import com.guciowons.yummify.dish.dto.DishListDTO;
import com.guciowons.yummify.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.entity.Dish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                IngredientMapper.class,
                TranslatedStringMapper.class,
        })
public interface DishMapper extends TranslatableMapper<Dish, DishManageDTO, DishClientDTO, DishListDTO> {

    DishManageDTO mapToManageDTO(Dish entity);

    DishClientDTO mapToClientDTO(Dish entity);

    DishListDTO mapToListDTO(Dish entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    Dish mapToSaveEntity(DishManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    Dish mapToUpdateEntity(DishManageDTO dto, @MappingTarget Dish entity);
}
