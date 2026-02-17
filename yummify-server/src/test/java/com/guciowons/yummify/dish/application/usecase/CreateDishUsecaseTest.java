package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishValidator;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenCreateDishCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateDishUsecaseTest {
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishValidator dishValidator = mock(DishValidator.class);

    private final CreateDishUsecase underTest = new CreateDishUsecase(dishRepository, dishValidator);

    @Test
    void shouldCreateDish() {
        // given
        var command = givenCreateDishCommand();

        // when
        var result = underTest.create(command);

        // then
        verify(dishValidator).validate(command.ingredientIds(), command.restaurantId());

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getDescription()).isEqualTo(command.description());
        assertThat(result.getIngredientIds()).isEqualTo(command.ingredientIds());
        assertThat(result.getImageId()).isNull();
    }

}