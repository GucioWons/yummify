package com.guciowons.yummify.dish.application.dto.mapper;

import com.guciowons.yummify.common.i8n.application.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.domain.entity.Dish;
import org.mapstruct.*;

import java.util.UUID;
import java.util.function.Function;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
        })
public interface DishMapper {

    @Mapping(target = "imageUrl", expression = "java(getImageUrl.apply(entity.getImageId()))")
    DishManageDTO mapToManageDTO(Dish entity, @Context Function<UUID, String> getImageUrl);

    @Mapping(target = "imageUrl", expression = "java(getImageUrl.apply(entity.getImageId()))")
    DishClientDTO mapToClientDTO(Dish entity, @Context Function<UUID, String> getImageUrl);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredientIds", ignore = true)
    Dish mapToSaveEntity(DishManageDTO dto, @Context UUID restaurantId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredientIds", ignore = true)
    Dish mapToUpdateEntity(DishManageDTO dto, @MappingTarget Dish entity);
}
