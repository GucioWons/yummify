package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.domain.service.DishIngredientService;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishUpdateUsecaseTest {
    private DishUpdateUsecase underTest;

    private final DishGetUsecase dishGetUsecase = mock(DishGetUsecase.class);
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishIngredientService dishIngredientService = mock(DishIngredientService.class);
    private final DishMapper dishMapper = mock(DishMapper.class);

    @BeforeEach
    void setUp() {
        underTest = new DishUpdateUsecase(dishGetUsecase, dishRepository, dishIngredientService, dishMapper);
    }

    @Test
    void shouldUpdateDish() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dto = DishFixture.emptyManageDTO();
        var existingDish = DishFixture.withIdEntity(dishId);
        var updatedDish = DishFixture.withIdEntity(dishId);
        var savedDish = DishFixture.withIdEntity(dishId);

        when(dishGetUsecase.getById(dishId, restaurantId)).thenReturn(existingDish);
        when(dishMapper.mapToUpdateEntity(dto, existingDish)).thenReturn(updatedDish);
        when(dishRepository.save(updatedDish)).thenReturn(savedDish);

        // when
        var result = underTest.update(dishId, dto, restaurantId);

        // then
        verify(dishGetUsecase).getById(dishId, restaurantId);
        verify(dishMapper).mapToUpdateEntity(dto, existingDish);
        verify(dishIngredientService).replaceIngredients(eq(updatedDish), any());
        verify(dishRepository).save(updatedDish);

        assertThat(result).isEqualTo(savedDish);
    }
}