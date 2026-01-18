package com.guciowons.yummify.restaurant.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantName;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantOwnerId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantDomainFixture {
    public static Restaurant givenRestaurant(int seed) {
        var restaurant = Restaurant.of(givenRestaurantName(seed), givenRestaurantDescription(seed), Language.EN);
        restaurant.changeOwner(givenRestaurantOwnerId(seed));
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
