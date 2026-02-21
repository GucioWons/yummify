package com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.JpaIngredient;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = JpaTranslatedStringMapper.class
)
public interface JpaIngredientMapper {
    Ingredient toDomain(JpaIngredient jpaIngredient);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    JpaIngredient toJpa(Ingredient ingredient);

    default Ingredient.Id toId(UUID id) {
        return Ingredient.Id.of(id);
    }

    default Ingredient.RestaurantId toRestaurantId(UUID id) {
        return Ingredient.RestaurantId.of(id);
    }
}
