package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientUpdateUsecaseTest {
    private IngredientUpdateUsecase underTest;

    private final IngredientGetUsecase ingredientGetUsecase = mock(IngredientGetUsecase.class);
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);
    private final IngredientMapper ingredientMapper = mock(IngredientMapper.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientUpdateUsecase(ingredientGetUsecase, ingredientRepository, ingredientMapper);
    }

    @Test
    void shouldUpdateIngredient() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var dto = IngredientFixture.emptyManageDTO();
        var existingIngredient = IngredientFixture.withIdEntity(ingredientId);
        var updatedIngredient = IngredientFixture.withIdEntity(ingredientId);
        var savedIngredient = IngredientFixture.withIdEntity(ingredientId);

        when(ingredientGetUsecase.getById(ingredientId, restaurantId)).thenReturn(existingIngredient);
        when(ingredientMapper.mapToUpdateEntity(dto, existingIngredient)).thenReturn(updatedIngredient);
        when(ingredientRepository.save(updatedIngredient)).thenReturn(savedIngredient);

        // when
        var result = underTest.update(ingredientId, dto, restaurantId);

        // then
        verify(ingredientGetUsecase).getById(ingredientId, restaurantId);
        verify(ingredientMapper).mapToUpdateEntity(dto, existingIngredient);
        verify(ingredientRepository).save(updatedIngredient);

        assertThat(result).isEqualTo(savedIngredient);
    }
}