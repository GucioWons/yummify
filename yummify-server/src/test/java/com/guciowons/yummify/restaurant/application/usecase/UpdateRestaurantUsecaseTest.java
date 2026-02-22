package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.restaurant.application.service.RestaurantLookupService;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.restaurant.application.fixture.RestaurantApplicationFixture.givenUpdateRestaurantCommand;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateRestaurantUsecaseTest {
    private final RestaurantLookupService restaurantLookupService = mock(RestaurantLookupService.class);
    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);

    private final UpdateRestaurantUsecase underTest = new UpdateRestaurantUsecase(
            restaurantLookupService,
            restaurantRepository
    );

    @Test
    void shouldUpdateRestaurant() {
        // given
        var command = givenUpdateRestaurantCommand();
        var restaurant = givenRestaurant(2);

        when(restaurantLookupService.getById(command.id())).thenReturn(restaurant);

        // when
        var result = underTest.update(command);

        // then
        verify(restaurantLookupService).getById(command.id());
        verify(restaurantRepository).save(restaurant);

        assertThat(result.getId()).isEqualTo(restaurant.getId());
        assertThat(result.getOwnerId()).isEqualTo(restaurant.getOwnerId());
        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getDescription()).isEqualTo(command.description());
        assertThat(result.getDefaultLanguage()).isEqualTo(command.defaultLanguage());
    }
}
