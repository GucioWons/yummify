package com.guciowons.yummify.dish.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishDomainFixture {
    public static Dish givenDish(int seed) {
        return Dish.of(
                givenRestaurantId(seed),
                givenDishName(seed),
                givenDishDescription(seed),
                givenDishIngredientIds(seed)
        );
    }

    public static DishId givenDishId(int seed) {
        return DishId.of(UUID.nameUUIDFromBytes(("dish-%s".formatted(seed)).getBytes()));
    }

    public static TranslatedString givenDishName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("dish-%s".formatted(seed))));
    }

    public static TranslatedString givenDishDescription(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("dish-%s".formatted(seed))));
    }

    public static List<UUID> givenDishIngredientIds(int seed) {
        return List.of(
                UUID.nameUUIDFromBytes("ingredient-%s".formatted(seed).getBytes()),
                UUID.nameUUIDFromBytes("ingredient-%s".formatted(seed + 1).getBytes()),
                UUID.nameUUIDFromBytes("ingredient-%s".formatted(seed + 2).getBytes())
        );
    }

    public static DishImageId givenDishImageId(int seed) {
        return DishImageId.of(UUID.nameUUIDFromBytes("image-%s".formatted(seed).getBytes()));
    }
}
