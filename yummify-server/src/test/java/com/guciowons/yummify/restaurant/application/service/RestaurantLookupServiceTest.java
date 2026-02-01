package com.guciowons.yummify.restaurant.application.service;

import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurant;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RestaurantLookupServiceTest {
    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    private final RestaurantLookupService underTest = new RestaurantLookupService(restaurantRepository);

    @Test
    void shouldGetRestaurant_WhenExists() {
        // given
        var restaurant = givenRestaurant(1);
        var restaurantId = restaurant.getId();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        // when
        var result = underTest.getById(restaurantId);

        // then
        verify(restaurantRepository).findById(restaurantId);

        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var restaurantId = givenRestaurantId(1);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getById(restaurantId)).isInstanceOf(RestaurantNotFoundException.class);

        // then
        verify(restaurantRepository).findById(restaurantId);
    }
}
