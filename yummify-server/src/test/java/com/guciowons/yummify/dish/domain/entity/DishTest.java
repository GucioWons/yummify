package com.guciowons.yummify.dish.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DishTest {

    @Test
    void shouldSetIngredientIds() {
        // given
        var oldIngredientIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        var newIngredientIds = List.of(UUID.randomUUID());

        var dish = new Dish();
        dish.setIngredientIds(oldIngredientIds);

        // when
        dish.setIngredientIds(newIngredientIds);

        // then
        assertThat(dish.getIngredientIds()).isEqualTo(newIngredientIds);
    }
}