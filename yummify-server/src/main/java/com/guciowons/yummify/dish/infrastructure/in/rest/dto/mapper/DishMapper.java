package com.guciowons.yummify.dish.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishClientDTO;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishManageDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
        })
public interface DishMapper {
    @Mapping(target = "id", source = "entity.id.value")
    DishManageDTO toManageDTO(Dish entity, String imageUrl);

    @Mapping(target = "id", source = "entity.id.value")
    DishClientDTO toClientDTO(Dish entity, String imageUrl);
}
