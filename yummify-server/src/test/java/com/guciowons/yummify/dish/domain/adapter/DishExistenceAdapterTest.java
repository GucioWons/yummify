package com.guciowons.yummify.dish.domain.adapter;

import com.guciowons.yummify.dish.domain.exception.DishErrorMessage;
import com.guciowons.yummify.dish.domain.exception.DishesNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DishExistenceAdapterTest {
    private DishExistenceAdapter underTest;

    private final DishRepository dishRepository = mock(DishRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new DishExistenceAdapter(dishRepository);
    }

    @Test
    void shouldNotThrowException_WhenNoMissingIds() {
        // given
        var restaurantId = UUID.randomUUID();
        var existingId = UUID.randomUUID();
        var dishIds = List.of(existingId);
        var dishes = List.of(DishFixture.withIdEntity(existingId));

        when(dishRepository.findByIdInAndRestaurantId(dishIds, restaurantId))
                .thenReturn(dishes);

        // when
        underTest.assertAllExist(dishIds, restaurantId);

        // then
        verify(dishRepository).findByIdInAndRestaurantId(dishIds, restaurantId);
    }

    @Test
    void shouldThrowException_WhenMissingIds() {
        // given
        var restaurantId = UUID.randomUUID();
        var existingId = UUID.randomUUID();
        var missingId = UUID.randomUUID();
        var dishIds = List.of(existingId, missingId);
        var existingDishes = List.of(DishFixture.withIdEntity(existingId));

        when(dishRepository.findByIdInAndRestaurantId(dishIds, restaurantId))
                .thenReturn(existingDishes);

        // when
        assertThatThrownBy(() -> underTest.assertAllExist(dishIds, restaurantId))
                .isInstanceOf(DishesNotFoundException.class)
                .satisfies(ex -> {
                    DishesNotFoundException e = (DishesNotFoundException) ex;
                    assertDishesNotFoundException(e, missingId);
                });

        // then
        verify(dishRepository).findByIdInAndRestaurantId(dishIds, restaurantId);
    }

    @Test
    void shouldNotCheckExistence_WhenDishIdsAreNull() {
        // given
        var restaurantId = UUID.randomUUID();

        // when
        underTest.assertAllExist(null, restaurantId);

        // then
        verify(dishRepository, never()).findByIdInAndRestaurantId(any(), any());
    }

    @Test
    void shouldNotCheckExistence_WhenDishIdsAreEmpty() {
        // given
        var restaurantId = UUID.randomUUID();

        // when
        underTest.assertAllExist(Collections.emptyList(), restaurantId);

        // then
        verify(dishRepository, never()).findByIdInAndRestaurantId(any(), any());
    }

    void assertDishesNotFoundException(DishesNotFoundException ex, UUID missingId) {
        assertThat(ex.getDomainError().errorMessage())
                .isEqualTo(DishErrorMessage.DISHES_NOT_FOUND_BY_ID);

        assertThat(ex.getDomainError().properties())
                .extractingByKey("ids")
                .asString()
                .contains(missingId.toString());
    }
}