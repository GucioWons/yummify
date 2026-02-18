package com.guciowons.yummify.dish.application.fixture;

import com.guciowons.yummify.dish.application.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static org.mockito.Mockito.mock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishApplicationFixture {
    public static CreateDishCommand givenCreateDishCommand() {
        return new CreateDishCommand(
                givenDishRestaurantId(1),
                givenDishName(1),
                givenDishDescription(1),
                givenDishIngredientIds(1)
        );
    }

    public static GetAllDishesQuery givenGetAllDishesCommand() {
        return new GetAllDishesQuery(givenDishRestaurantId(1));
    }

    public static GetDishQuery givenGetDishCommand() {
        return new GetDishQuery(givenDishId(1), givenDishRestaurantId(1));
    }

    public static UpdateDishCommand givenUpdateDishCommand() {
        return new UpdateDishCommand(
                givenDishId(1),
                givenDishRestaurantId(1),
                givenDishName(1),
                givenDishDescription(1),
                givenDishIngredientIds(1)
        );
    }

    public static UpdateDishImageCommand givenUpdateDishImageCommand() {
        return new UpdateDishImageCommand(
                givenDishId(1),
                mock(MultipartFile.class),
                givenDishRestaurantId(1)
        );
    }
}
