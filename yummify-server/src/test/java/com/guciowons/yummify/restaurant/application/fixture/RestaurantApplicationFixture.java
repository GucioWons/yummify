package com.guciowons.yummify.restaurant.application.fixture;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantApplicationFixture {
    public static CreateRestaurantCommand givenCreateRestaurantCommand() {
        return new CreateRestaurantCommand(
                givenRestaurantName(1),
                givenRestaurantDescription(1),
                Language.EN,
                givenRestaurantOwner()
        );
    }

    public static GetRestaurantCommand givenGetRestaurantCommand() {
        return new GetRestaurantCommand(givenRestaurantId(1));
    }

    public static UpdateRestaurantCommand givenUpdateRestaurantCommand() {
        return new UpdateRestaurantCommand(
                givenRestaurantId(1),
                givenRestaurantName(1),
                givenRestaurantDescription(1),
                Language.EN
        );
    }

    public static RestaurantOwner givenRestaurantOwner() {
        return new RestaurantOwner("john.doe@example.com", "johndoe", "John", "Doe");
    }
}
