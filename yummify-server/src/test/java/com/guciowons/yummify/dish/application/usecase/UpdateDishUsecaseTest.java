package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.application.service.DishValidator;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenUpdateDishCommand;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateDishUsecaseTest {
    private final DishLookupService dishLookupService = mock(DishLookupService.class);
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishValidator dishValidator = mock(DishValidator.class);

    private final UpdateDishUsecase underTest = new UpdateDishUsecase(dishLookupService, dishRepository, dishValidator);

    @Test
    void shouldUpdateDish() {
        // given
        var command = givenUpdateDishCommand();
        var dish = givenDish(2);

        when(dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(dish);
        when(dishRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        var result = underTest.update(command);

        // then
        verify(dishValidator).validate(command.ingredientIds(), command.restaurantId());
        verify(dishLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(dishRepository).save(any());

        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getDescription()).isEqualTo(command.description());
        assertThat(result.getIngredientIds()).isEqualTo(command.ingredientIds());
    }
}
