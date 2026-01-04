package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishGetAllUsecaseTest {
    private DishGetAllUsecase underTest;

    private final DishRepository dishRepository = mock(DishRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new DishGetAllUsecase(dishRepository);
    }

    @Test
    void shouldGetAllDishes() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishes = List.of(DishFixture.withIdEntity(UUID.randomUUID()));

        when(dishRepository.findAllByRestaurantId(restaurantId)).thenReturn(dishes);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(dishRepository).findAllByRestaurantId(restaurantId);

        assertThat(result).isEqualTo(dishes);
    }
}