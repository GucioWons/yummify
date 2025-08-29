package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DishMapper {
    @Mapping(target = "description", expression = "java(entity.getDescription().get())")
    DishDTO<String> mapToClientDTO(Dish dish);

    DishDTO<TranslatedStringDTO> mapToAdminDTO(Dish dish);

    @Mapping(target = "id", ignore = true)
    Dish mapToEntity(DishDTO<TranslatedStringDTO> dto);

    @Mapping(target = "id", ignore = true)
    Dish mapToUpdateEntity(DishDTO<TranslatedStringDTO> dto, @MappingTarget Dish dish);
}
