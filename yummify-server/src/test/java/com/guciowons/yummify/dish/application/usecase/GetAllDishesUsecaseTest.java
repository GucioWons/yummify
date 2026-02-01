package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenGetAllDishesCommand;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllDishesUsecaseTest {
    private final DishRepository dishRepository = mock(DishRepository.class);

    private final GetAllDishesUsecase underTest = new GetAllDishesUsecase(dishRepository);

    @Test
    void shouldGetAllDishes() {
        // given
        var command = givenGetAllDishesCommand();
        var dishes = List.of(givenDish(1), givenDish(2), givenDish(3));

        when(dishRepository.findAllByRestaurantId(command.restaurantId())).thenReturn(dishes);

        // when
        var result = underTest.getAll(command);

        // then
        verify(dishRepository).findAllByRestaurantId(command.restaurantId());

        assertThat(result).isEqualTo(dishes);
    }

}