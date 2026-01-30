package com.guciowons.yummify.ingredient.application.fixture;

import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsCommand;
import com.guciowons.yummify.ingredient.application.model.GetIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientName;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientApplicationFixture {
    public static CreateIngredientCommand givenCreateIngredientCommand() {
        return new CreateIngredientCommand(givenRestaurantId(1), givenIngredientName(1));
    }

    public static GetAllIngredientsCommand givenGetAllIngredientsCommand() {
        return new GetAllIngredientsCommand(givenRestaurantId(1));
    }

    public static GetIngredientCommand givenGetIngredientCommand() {
        return new GetIngredientCommand(givenIngredientId(1), givenRestaurantId(1));
    }

    public static UpdateIngredientCommand givenUpdateIngredientCommand() {
        return new UpdateIngredientCommand(givenIngredientId(1), givenRestaurantId(1), givenIngredientName(1));
    }
}
