package com.guciowons.yummify.restaurant.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantDomainFixture {
    public static Restaurant givenRestaurant(int seed) {
        return new Restaurant(
                givenRestaurantId(seed),
                givenRestaurantOwnerId(seed),
                givenRestaurantName(seed),
                givenRestaurantDescription(seed),
                Language.EN
        );
    }

    public static Restaurant.Id givenRestaurantId(int seed) {
        return Restaurant.Id.of(UUID.nameUUIDFromBytes(("restaurant-%s".formatted(seed)).getBytes()));
    }

    public static Restaurant.Name givenRestaurantName(int seed) {
        return Restaurant.Name.of("Restaurant name %s".formatted(seed));
    }

    public static TranslatedString givenRestaurantDescription(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("Restaurant description %s".formatted(seed))));
    }

    public static Restaurant.OwnerId givenRestaurantOwnerId(int seed) {
        return Restaurant.OwnerId.of(UUID.nameUUIDFromBytes(("owner-%s".formatted(seed)).getBytes()));
    }
}
