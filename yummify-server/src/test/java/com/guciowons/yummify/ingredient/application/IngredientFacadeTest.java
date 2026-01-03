package com.guciowons.yummify.ingredient.application;

import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.application.usecase.IngredientCreateUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetAllUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientUpdateUsecase;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientFacadeTest {
    private IngredientFacade underTest;

    private final IngredientCreateUsecase ingredientCreateUsecase = mock(IngredientCreateUsecase.class);
    private final IngredientGetAllUsecase ingredientGetAllUsecase = mock(IngredientGetAllUsecase.class);
    private final IngredientGetUsecase ingredientGetUsecase = mock(IngredientGetUsecase.class);
    private final IngredientUpdateUsecase ingredientUpdateUsecase = mock(IngredientUpdateUsecase.class);
    private final IngredientMapper ingredientMapper = mock(IngredientMapper.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientFacade(
                ingredientCreateUsecase,
                ingredientGetAllUsecase,
                ingredientGetUsecase,
                ingredientUpdateUsecase,
                ingredientMapper
        );
    }

    @Test
    void shouldCreateIngredient() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var dto = IngredientFixture.emptyManageDTO();
        var ingredient = IngredientFixture.withIdEntity(ingredientId);
        var expectedResult = IngredientFixture.withIdManageDTO(ingredientId);

        when(ingredientCreateUsecase.create(dto, restaurantId)).thenReturn(ingredient);
        when(ingredientMapper.mapToManageDTO(ingredient)).thenReturn(expectedResult);

        // when
        var result = underTest.create(dto, restaurantId);

        // then
        verify(ingredientCreateUsecase).create(dto, restaurantId);
        verify(ingredientMapper).mapToManageDTO(ingredient);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldGetAllIngredients() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var ingredient = IngredientFixture.withIdEntity(ingredientId);
        var ingredients = List.of(ingredient);
        var dto = IngredientFixture.withIdClientDTO(ingredientId);
        var expectedResult = List.of(dto);

        when(ingredientGetAllUsecase.getAll(restaurantId)).thenReturn(ingredients);
        when(ingredientMapper.mapToClientDTO(ingredient)).thenReturn(dto);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(ingredientGetAllUsecase).getAll(restaurantId);
        verify(ingredientMapper).mapToClientDTO(ingredient);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldGetManageDTO() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var ingredient = IngredientFixture.withIdEntity(ingredientId);
        var expectedResult = IngredientFixture.withIdManageDTO(ingredientId);

        when(ingredientGetUsecase.getById(ingredientId, restaurantId)).thenReturn(ingredient);
        when(ingredientMapper.mapToManageDTO(ingredient)).thenReturn(expectedResult);
        // when
        var result = underTest.getManageDTO(ingredientId, restaurantId);

        // then
        verify(ingredientGetUsecase).getById(ingredientId, restaurantId);
        verify(ingredientMapper).mapToManageDTO(ingredient);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldUpdateIngredient() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var dto = IngredientFixture.withIdManageDTO(ingredientId);
        var ingredient = IngredientFixture.withIdEntity(ingredientId);
        var expectedResult = IngredientFixture.withIdManageDTO(ingredientId);

        when(ingredientUpdateUsecase.update(ingredientId, dto, restaurantId)).thenReturn(ingredient);
        when(ingredientMapper.mapToManageDTO(ingredient)).thenReturn(expectedResult);

        // when
        var result = underTest.update(ingredientId, dto, restaurantId);

        // then
        verify(ingredientUpdateUsecase).update(ingredientId, dto, restaurantId);
        verify(ingredientMapper).mapToManageDTO(ingredient);

        assertThat(result).isEqualTo(expectedResult);
    }
}