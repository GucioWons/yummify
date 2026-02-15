package com.guciowons.yummify.ingredient.application.fixture;

import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsQuery;
import com.guciowons.yummify.ingredient.application.model.GetIngredientQuery;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientApplicationFixture {
    public static CreateIngredientCommand givenCreateIngredientCommand() {
        return new CreateIngredientCommand(givenIngredientRestaurantId(1), givenIngredientName(1));
    }

    public static GetAllIngredientsQuery givenGetAllIngredientsQuery() {
        return new GetAllIngredientsQuery(givenIngredientRestaurantId(1));
    }

    public static GetIngredientQuery givenGetIngredientQuery() {
        return new GetIngredientQuery(givenIngredientId(1), givenIngredientRestaurantId(1));
    }

    public static UpdateIngredientCommand givenUpdateIngredientCommand() {
        return new UpdateIngredientCommand(givenIngredientId(1), givenIngredientRestaurantId(1), givenIngredientName(1));
    }
}
