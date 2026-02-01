package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.restaurant.application.service.RestaurantLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.restaurant.application.fixture.RestaurantApplicationFixture.givenGetRestaurantCommand;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetRestaurantUsecaseTest {
    private final RestaurantLookupService restaurantLookupService = mock(RestaurantLookupService.class);
    private final GetRestaurantUsecase underTest = new GetRestaurantUsecase(restaurantLookupService);

    @Test
    void shouldGetRestaurant() {
        // given
        var command = givenGetRestaurantCommand();
        var restaurant = givenRestaurant(1);

        when(restaurantLookupService.getById(command.id())).thenReturn(restaurant);

        // when
        var result = underTest.get(command);

        // then
        verify(restaurantLookupService).getById(command.id());

        assertThat(result).isEqualTo(restaurant);
    }
}
