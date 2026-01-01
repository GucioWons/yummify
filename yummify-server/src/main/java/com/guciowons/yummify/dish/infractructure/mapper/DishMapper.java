package com.guciowons.yummify.dish.infractructure.mapper;

import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                IngredientMapper.class,
                TranslatedStringMapper.class,
        })
public interface DishMapper extends TranslatableMapper<Dish, DishManageDTO, DishClientDTO> {

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
