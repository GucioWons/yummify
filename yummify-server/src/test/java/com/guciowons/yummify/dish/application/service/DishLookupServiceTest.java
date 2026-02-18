package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DishLookupServiceTest {
    private final DishRepository dishRepository = mock(DishRepository.class);

    private final DishLookupService underTest = new DishLookupService(dishRepository);

    @Test
    void shouldGetDish_WhenExists() {
        // given
        var dish = givenDish(1);
        var dishId = dish.getId();
        var restaurantId = dish.getRestaurantId();

        when(dishRepository.findByIdAndRestaurantId(dishId, restaurantId)).thenReturn(Optional.of(dish));

        // when
        var result = underTest.getByIdAndRestaurantId(dishId, restaurantId);

        // then
        verify(dishRepository).findByIdAndRestaurantId(dishId, restaurantId);

        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var dishId = givenDishId(1);
        var restaurantId = givenDishRestaurantId(1);

        when(dishRepository.findByIdAndRestaurantId(dishId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(dishId, restaurantId))
                .isInstanceOf(DishNotFoundException.class);

        // then
        verify(dishRepository).findByIdAndRestaurantId(dishId, restaurantId);
    }

}