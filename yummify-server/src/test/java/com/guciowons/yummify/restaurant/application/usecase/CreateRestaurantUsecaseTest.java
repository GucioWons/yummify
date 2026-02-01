package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantOwnerId;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.guciowons.yummify.restaurant.application.fixture.RestaurantApplicationFixture.givenCreateRestaurantCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CreateRestaurantUsecaseTest {
    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    private final AuthFacadePort authFacadePort = mock(AuthFacadePort.class);

    private final CreateRestaurantUsecase underTest = new CreateRestaurantUsecase(restaurantRepository, authFacadePort);

    @Test
    void shouldCreateRestaurant() {
        // given
        var command = givenCreateRestaurantCommand();
        var ownerId = UUID.nameUUIDFromBytes("user".getBytes());

        when(authFacadePort.createUser(
                eq(command.owner().email()),
                eq(command.owner().username()),
                eq(command.owner().firstName()),
                eq(command.owner().lastName()),
                any(),
                eq(true))
        ).thenReturn(ownerId);

        when(restaurantRepository.save(any(Restaurant.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        var result = underTest.create(command);

        // then
        verify(authFacadePort).createUser(
                eq(command.owner().email()),
                eq(command.owner().username()),
                eq(command.owner().firstName()),
                eq(command.owner().lastName()),
                eq(result.getId().value()),
                eq(true)
        );
        verify(restaurantRepository).save(result);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getOwnerId()).isEqualTo(RestaurantOwnerId.of(ownerId));
        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getDescription()).isEqualTo(command.description());
        assertThat(result.getDefaultLanguage()).isEqualTo(command.defaultLanguage());
    }
}
