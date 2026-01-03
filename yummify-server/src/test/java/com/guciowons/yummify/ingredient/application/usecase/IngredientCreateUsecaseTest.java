package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class IngredientCreateUsecaseTest {
    private IngredientCreateUsecase underTest;

    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);
    private final IngredientMapper ingredientMapper = mock(IngredientMapper.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientCreateUsecase(ingredientRepository, ingredientMapper);
    }

    @Test
    void shouldCreateIngredient() {
        // given
        var restaurantId = UUID.randomUUID();
        var dto = IngredientFixture.emptyManageDTO();
        var mappedIngredient = IngredientFixture.emptyEntity();
        var savedIngredient = IngredientFixture.withIdEntity(UUID.randomUUID());

        when(ingredientMapper.mapToSaveEntity(dto, restaurantId)).thenReturn(mappedIngredient);
        when(ingredientRepository.save(mappedIngredient)).thenReturn(savedIngredient);

        // when
        var result = underTest.create(dto, restaurantId);

        // then
        verify(ingredientMapper).mapToSaveEntity(dto, restaurantId);
        verify(ingredientRepository).save(mappedIngredient);

        assertThat(result).isEqualTo(savedIngredient);
    }
}