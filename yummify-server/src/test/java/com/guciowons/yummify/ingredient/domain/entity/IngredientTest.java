package com.guciowons.yummify.ingredient.domain.entity;

import org.junit.jupiter.api.Test;


import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class IngredientTest {
    @Test
    void shouldCreateIngredientWithRandomId() {
        // given
        var restaurantId = givenIngredientRestaurantId(1);
        var name = givenIngredientName(1);

        // when
        var result = Ingredient.create(restaurantId, name);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    void shouldUpdateDetails() {
        // given
        var ingredient = givenIngredient(1);
        var newName = givenIngredientName(2);

        // when
        ingredient.updateDetails(newName);

        // then
        assertThat(ingredient.getName()).isEqualTo(newName);
    }
}
