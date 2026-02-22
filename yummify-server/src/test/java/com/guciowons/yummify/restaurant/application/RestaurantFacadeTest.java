package com.guciowons.yummify.restaurant.application;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.application.model.mapper.RestaurantCommandMapper;
import com.guciowons.yummify.restaurant.application.usecase.CreateRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.GetRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.UpdateRestaurantUsecase;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.guciowons.yummify.restaurant.application.fixture.RestaurantApplicationFixture.*;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RestaurantFacadeTest {
    private final CreateRestaurantUsecase createRestaurantUsecase = mock(CreateRestaurantUsecase.class);
    private final GetRestaurantUsecase getRestaurantUsecase = mock(GetRestaurantUsecase.class);
    private final UpdateRestaurantUsecase updateRestaurantUsecase = mock(UpdateRestaurantUsecase.class);
    private final RestaurantCommandMapper restaurantCommandMapper = mock(RestaurantCommandMapper.class);

    private final RestaurantFacade underTest = new RestaurantFacade(
            createRestaurantUsecase,
            getRestaurantUsecase,
            updateRestaurantUsecase,
            restaurantCommandMapper
    );

    @Test
    void shouldCreateRestaurant() {
        // given
        var name = givenRestaurantName(1).value();
        var description = Map.of("EN", "Italian restaurant");
        var defaultLanguage = Language.EN.name();
        var restaurantOwner = givenRestaurantOwner();
        var command = givenCreateRestaurantCommand();
        var restaurant = givenRestaurant(1);

        when(restaurantCommandMapper.toCreateRestaurantCommand(name, description, defaultLanguage, restaurantOwner))
                .thenReturn(givenCreateRestaurantCommand());
        when(createRestaurantUsecase.create(command)).thenReturn(restaurant);

        // when
        var result = underTest.create(name, description, defaultLanguage, restaurantOwner);

        // then
        verify(restaurantCommandMapper).toCreateRestaurantCommand(name, description, defaultLanguage, restaurantOwner);
        verify(createRestaurantUsecase).create(command);

        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void shouldGetRestaurant() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGetRestaurantCommand();
        var restaurant = givenRestaurant(1);

        when(restaurantCommandMapper.toGetRestaurantCommand(restaurantId)).thenReturn(command);
        when(getRestaurantUsecase.get(command)).thenReturn(restaurant);

        // when
        var result = underTest.getById(restaurantId);

        // then
        verify(restaurantCommandMapper).toGetRestaurantCommand(restaurantId);
        verify(getRestaurantUsecase).get(command);

        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void shouldUpdateRestaurant() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var name = givenRestaurantName(1).value();
        var description = Map.of("EN", "Italian restaurant");
        var defaultLanguage = Language.EN.name();
        var command = givenUpdateRestaurantCommand();
        var restaurant = givenRestaurant(1);

        when(restaurantCommandMapper.toUpdateRestaurantCommand(restaurantId, name, description, defaultLanguage))
                .thenReturn(command);
        when(updateRestaurantUsecase.update(command)).thenReturn(restaurant);

        // when
        var result = underTest.update(restaurantId, name, description, defaultLanguage);

        // then
        verify(restaurantCommandMapper).toUpdateRestaurantCommand(restaurantId, name, description, defaultLanguage);
        verify(updateRestaurantUsecase).update(command);

        assertThat(result).isEqualTo(restaurant);
    }
}
