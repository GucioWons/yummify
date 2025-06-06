package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishDTO mapToDTO(Dish dish);

    @Mapping(target = "id", ignore = true)
    Dish mapToEntity(DishDTO dto);

    @Mapping(target = "id", ignore = true)
    Dish mapToUpdateEntity(DishDTO dto, @MappingTarget Dish dish);
}
