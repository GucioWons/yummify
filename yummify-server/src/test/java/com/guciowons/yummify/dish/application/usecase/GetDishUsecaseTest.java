package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenGetDishCommand;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetDishUsecaseTest {
    private final DishLookupService dishLookupService = mock(DishLookupService.class);
    private final GetDishUsecase underTest = new GetDishUsecase(dishLookupService);

    @Test
    void shouldGetDish() {
        // given
        var command = givenGetDishCommand();
        var dish = givenDish(1);

        when(dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(dish);

        // when
        var result = underTest.getById(command);

        // then
        verify(dishLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());

        assertThat(result).isEqualTo(dish);
    }
}
