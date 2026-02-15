package com.guciowons.yummify.ingredient.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientDomainFixture {
    public static Ingredient givenIngredient(int seed) {
        return new Ingredient(givenIngredientId(seed), givenIngredientRestaurantId(seed), givenIngredientName(seed));
    }

    public static Ingredient.Id givenIngredientId(int seed) {
        return Ingredient.Id.of(UUID.nameUUIDFromBytes("ingredient-%s".formatted(seed).getBytes()));
    }

    public static Ingredient.RestaurantId givenIngredientRestaurantId(int seed) {
        return Ingredient.RestaurantId.of(UUID.nameUUIDFromBytes("restaurant-%s".formatted(seed).getBytes()));
    }

    public static TranslatedString givenIngredientName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("ingredient-%s".formatted(seed))));
    }
}
