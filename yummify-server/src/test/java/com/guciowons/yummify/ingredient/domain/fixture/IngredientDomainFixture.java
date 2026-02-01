package com.guciowons.yummify.ingredient.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientDomainFixture {
    public static Ingredient givenIngredient(int seed) {
        return Ingredient.of(givenRestaurantId(seed), givenIngredientName(seed));
    }

    public static IngredientId givenIngredientId(int seed) {
        return IngredientId.of(UUID.nameUUIDFromBytes("ingredient-%s".formatted(seed).getBytes()));
    }

    public static TranslatedString givenIngredientName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("ingredient-%s".formatted(seed))));
    }
}
