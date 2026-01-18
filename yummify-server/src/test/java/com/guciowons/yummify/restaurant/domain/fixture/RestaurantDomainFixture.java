package com.guciowons.yummify.restaurant.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantName;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantOwnerId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantDomainFixture {
    public static Restaurant givenRestaurant(int seed) {
        return givenRestaurantBuilder(seed).build();
    }

    public static RestaurantBuilder givenRestaurantBuilder(int seed) {
        return builder()
                .name(givenRestaurantName(seed))
                .description(givenRestaurantDescription(seed))
                .defaultLanguage(Language.EN)
                .ownerId(givenRestaurantOwnerId(seed));
    }

    @Builder
    private static Restaurant createRestaurant(
            RestaurantName name,
            TranslatedString description,
            Language defaultLanguage,
            RestaurantOwnerId ownerId
    ) {
        var restaurant = Restaurant.of(name, description, defaultLanguage);

        if (ownerId != null) {
            restaurant.changeOwner(ownerId);
        }
        return restaurant;
    }

    public static RestaurantId givenRestaurantId(int seed) {
        return RestaurantId.of(UUID.nameUUIDFromBytes(("restaurant-%s".formatted(seed)).getBytes()));
    }

    public static RestaurantName givenRestaurantName(int seed) {
        return RestaurantName.of("Restaurant name %s".formatted(seed));
    }

    public static TranslatedString givenRestaurantDescription(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("Restaurant description %s".formatted(seed))));
    }

    public static RestaurantOwnerId givenRestaurantOwnerId(int seed) {
        return RestaurantOwnerId.of(UUID.nameUUIDFromBytes(("owner-%s".formatted(seed)).getBytes()));
    }
}
