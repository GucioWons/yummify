package com.guciowons.yummify.dish.application.fixture;

import com.guciowons.yummify.dish.application.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.mockito.Mockito.mock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishApplicationFixture {
    public static CreateDishCommand givenCreateDishCommand() {
        return new CreateDishCommand(
                givenRestaurantId(1),
                givenDishName(1),
                givenDishDescription(1),
                givenDishIngredientIds(1)
        );
    }

    public static GetAllDishesCommand givenGetAllDishesCommand() {
        return new GetAllDishesCommand(givenRestaurantId(1));
    }

    public static GetDishCommand givenGetDishCommand() {
        return new GetDishCommand(givenDishId(1), givenRestaurantId(1));
    }

    public static UpdateDishCommand givenUpdateDishCommand() {
        return new UpdateDishCommand(
                givenDishId(1),
                givenRestaurantId(1),
                givenDishName(1),
                givenDishDescription(1),
                givenDishIngredientIds(1)
        );
    }

    public static UpdateDishImageCommand givenUpdateDishImageCommand() {
        return new UpdateDishImageCommand(
                givenDishId(1),
                mock(MultipartFile.class),
                givenRestaurantId(1)
        );
    }
}
