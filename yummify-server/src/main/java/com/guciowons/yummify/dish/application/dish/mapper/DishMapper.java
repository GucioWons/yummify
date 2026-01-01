package com.guciowons.yummify.dish.application.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.dish.application.dish.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import com.guciowons.yummify.dish.application.ingredient.mapper.IngredientMapper;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                IngredientMapper.class,
                TranslatedStringMapper.class,
        })
public interface DishMapper {

    @Mapping(target = "imageUrl", source = "imageUrl")
    DishManageDTO mapToManageDTO(Dish entity, @Context String imageUrl);

    @Mapping(target = "imageUrl", source = "imageUrl")
    DishClientDTO mapToClientDTO(Dish entity, @Context String imageUrl);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    Dish mapToSaveEntity(DishManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    Dish mapToUpdateEntity(DishManageDTO dto, @MappingTarget Dish entity);
}
