package com.guciowons.yummify.ingredient.application;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.ingredient.application.model.mapper.IngredientCommandMapper;
import com.guciowons.yummify.ingredient.application.usecase.CreateIngredientUsecase;
import com.guciowons.yummify.ingredient.application.usecase.GetAllIngredientsUsecase;
import com.guciowons.yummify.ingredient.application.usecase.GetIngredientUsecase;
import com.guciowons.yummify.ingredient.application.usecase.UpdateIngredientUsecase;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.function.Supplier;

import static com.guciowons.yummify.ingredient.application.fixture.IngredientApplicationFixture.*;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.*;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientFacadeTest {
    private final CreateIngredientUsecase createIngredientUsecase = mock(CreateIngredientUsecase.class);
    private final GetAllIngredientsUsecase getAllIngredientsUsecase = mock(GetAllIngredientsUsecase.class);
    private final GetIngredientUsecase getIngredientUsecase = mock(GetIngredientUsecase.class);
    private final UpdateIngredientUsecase updateIngredientUsecase = mock(UpdateIngredientUsecase.class);
    private final DomainExceptionHandler ingredientDomainExceptionHandler = mock(DomainExceptionHandler.class);
    private final IngredientCommandMapper ingredientCommandMapper = mock(IngredientCommandMapper.class);

    private final IngredientFacade underTest = new IngredientFacade(
            createIngredientUsecase,
            getAllIngredientsUsecase,
            getIngredientUsecase,
            updateIngredientUsecase,
            ingredientDomainExceptionHandler,
            ingredientCommandMapper
    );

    @Test
    void shouldCreateIngredient() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var name = givenIngredientName(1);
        var command = givenCreateIngredientCommand();
        var ingredient = givenIngredient(1);

        when(ingredientCommandMapper.toCreateIngredientCommand(restaurantId, name)).thenReturn(command);
        when(createIngredientUsecase.create(command)).thenReturn(ingredient);

        // when
        var result = underTest.create(restaurantId, name);

        // then
        verify(ingredientCommandMapper).toCreateIngredientCommand(restaurantId, name);
        verify(createIngredientUsecase).create(command);

        assertThat(result).isEqualTo(ingredient);
    }

    @Test
    void shouldGetAllIngredients() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGetAllIngredientsCommand();
        var ingredients = List.of(givenIngredient(1), givenIngredient(2), givenIngredient(3));

        when(ingredientCommandMapper.toGetAllIngredientsCommand(restaurantId)).thenReturn(command);
        when(getAllIngredientsUsecase.getAll(command)).thenReturn(ingredients);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(ingredientCommandMapper).toGetAllIngredientsCommand(restaurantId);
        verify(getAllIngredientsUsecase).getAll(command);

        assertThat(result).isEqualTo(ingredients);
    }

    @Test
    void shouldGetIngredient() {
        // given
        var ingredientId = givenIngredientId(1).value();
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGetIngredientCommand();
        var ingredient = givenIngredient(1);

        when(ingredientCommandMapper.toGetIngredientCommand(ingredientId, restaurantId)).thenReturn(command);
        when(ingredientDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Ingredient>>any()))
                .thenAnswer(inv -> inv.<Supplier<Ingredient>>getArgument(0).get());
        when(getIngredientUsecase.getById(command)).thenReturn(ingredient);

        // when
        var result = underTest.getById(ingredientId, restaurantId);

        // then
        verify(ingredientCommandMapper).toGetIngredientCommand(ingredientId, restaurantId);
        verify(ingredientDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Ingredient>>any());
        verify(getIngredientUsecase).getById(command);

        assertThat(result).isEqualTo(ingredient);
    }

    @Test
    void shouldUpdateIngredient() {
        // given
        var ingredientId = givenIngredientId(1).value();
        var restaurantId = givenRestaurantId(1).value();
        var name = givenIngredientName(1);
        var command = givenUpdateIngredientCommand();
        var ingredient = givenIngredient(1);

        when(ingredientCommandMapper.toUpdateIngredientCommand(ingredientId, restaurantId, name)).thenReturn(command);
        when(ingredientDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Ingredient>>any()))
                .thenAnswer(inv -> inv.<Supplier<Ingredient>>getArgument(0).get());
        when(updateIngredientUsecase.update(command)).thenReturn(ingredient);

        // when
        var result = underTest.update(ingredientId, restaurantId, name);

        // then
        verify(ingredientCommandMapper).toUpdateIngredientCommand(ingredientId, restaurantId, name);
        verify(ingredientDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Ingredient>>any());
        verify(updateIngredientUsecase).update(command);

        assertThat(result).isEqualTo(ingredient);
    }
}
