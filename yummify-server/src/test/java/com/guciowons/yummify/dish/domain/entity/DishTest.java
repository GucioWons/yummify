package com.guciowons.yummify.dish.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class DishTest {
    @Test
    void shouldCreateDishWithRandomId() {
        // given
        var restaurantId = givenDishRestaurantId(1);
        var name = givenDishName(1);
        var description = givenDishDescription(1);
        var ingredientIds = givenDishIngredientIds(1);

        // when
        var result = Dish.create(restaurantId, name, description, ingredientIds);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getIngredientIds()).isEqualTo(ingredientIds);
    }

    @Test
    void shouldUpdateDetails() {
        // given
        var dish = givenDish(1);
        var name = givenDishName(2);
        var description = givenDishDescription(2);
        var ingredientIds = givenDishIngredientIds(3);

        // when
        dish.updateDetails(name, description, ingredientIds);

        // then
        assertThat(dish.getName()).isEqualTo(name);
        assertThat(dish.getDescription()).isEqualTo(description);
        assertThat(dish.getIngredientIds()).isEqualTo(ingredientIds);
    }

    @Test
    void shouldChangeImage() {
        // given
        var dish = givenDish(1);
        var imageId = givenDishImageId(1);

        // when
        dish.changeImage(imageId);

        // then
        assertThat(dish.getImageId()).isEqualTo(imageId);
    }
}
