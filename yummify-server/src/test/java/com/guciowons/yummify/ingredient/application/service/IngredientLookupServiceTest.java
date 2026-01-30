package com.guciowons.yummify.ingredient.application.service;

import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredient;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class IngredientLookupServiceTest {
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    private final IngredientLookupService underTest = new IngredientLookupService(ingredientRepository);

    @Test
    void shouldGetIngredient_WhenExists() {
        // given
        var ingredient = givenIngredient(1);
        var ingredientId = ingredient.getId();
        var restaurantId = ingredient.getRestaurantId();

        when(ingredientRepository.findByIdAndRestaurantId(ingredientId, restaurantId)).thenReturn(Optional.of(ingredient));

        // when
        var result = underTest.getByIdAndRestaurantId(ingredientId, restaurantId);

        // then
        verify(ingredientRepository).findByIdAndRestaurantId(ingredientId, restaurantId);

        assertThat(result).isEqualTo(ingredient);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var ingredientId = givenIngredientId(1);
        var restaurantId = givenRestaurantId(1);

        when(ingredientRepository.findByIdAndRestaurantId(ingredientId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(ingredientId, restaurantId))
                .isInstanceOf(IngredientNotFoundException.class);

        // then
        verify(ingredientRepository).findByIdAndRestaurantId(ingredientId, restaurantId);
    }
}
