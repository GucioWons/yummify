package com.guciowons.yummify.restaurant.domain.entity;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {
    @Test
    void shouldCreateRestaurantWithRandomIdAndNoOwner() {
        // given
        var name = givenRestaurantName(2);
        var description = givenRestaurantDescription(2);
        var defaultLanguage = Language.EN;

        // when
        var result = Restaurant.of(name, description, defaultLanguage);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getOwnerId()).isNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getDefaultLanguage()).isEqualTo(defaultLanguage);
    }

    @Test
    void shouldChangeOwner() {
        // given
        var restaurant = givenRestaurant(1);
        var newOwnerId = givenRestaurantOwnerId(2);

        // when
        restaurant.changeOwner(newOwnerId);

        // then
        assertThat(restaurant.getOwnerId()).isEqualTo(newOwnerId);
    }

    @Test
    void shouldUpdateDetails() {
        // given
        var restaurant = givenRestaurant(1);
        var newName = givenRestaurantName(2);
        var newDescription = givenRestaurantDescription(2);
        var newDefaultLanguage = Language.DE;

        // when
        restaurant.updateDetails(newName, newDescription, newDefaultLanguage);

        // then
        assertThat(restaurant.getName()).isEqualTo(newName);
        assertThat(restaurant.getDescription()).isEqualTo(newDescription);
        assertThat(restaurant.getDefaultLanguage()).isEqualTo(newDefaultLanguage);
    }
}
