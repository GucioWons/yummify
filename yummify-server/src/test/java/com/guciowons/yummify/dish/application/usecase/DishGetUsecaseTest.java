package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.domain.exception.DishErrorMessage;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DishGetUsecaseTest {
    private DishGetUsecase underTest;

    private final DishRepository dishRepository = mock(DishRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new DishGetUsecase(dishRepository);
    }

    @Test
    void shouldReturnDish() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dish = DishFixture.withIdEntity(dishId);

        when(dishRepository.findByIdAndRestaurantId(dishId, restaurantId))
                .thenReturn(Optional.of(dish));

        // when
        var result = underTest.getById(dishId, restaurantId);

        // then
        verify(dishRepository).findByIdAndRestaurantId(dishId, restaurantId);

        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldThrowException_WhenDishNotFound() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();

        when(dishRepository.findByIdAndRestaurantId(dishId, restaurantId))
                .thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getById(dishId, restaurantId))
                .isInstanceOf(DishNotFoundException.class)
                .satisfies(ex -> {
                    DishNotFoundException e = (DishNotFoundException) ex;
                    assertDishNotFoundException(e, dishId);
                });

        // then
        verify(dishRepository).findByIdAndRestaurantId(dishId, restaurantId);
    }

    void assertDishNotFoundException(DishNotFoundException ex, UUID dishId) {
        assertThat(ex.getDomainError().errorMessage())
                .isEqualTo(DishErrorMessage.DISH_NOT_FOUND_BY_ID);

        assertThat(ex.getDomainError().properties())
                .extractingByKey("id")
                .asString()
                .contains(dishId.toString());
    }
}