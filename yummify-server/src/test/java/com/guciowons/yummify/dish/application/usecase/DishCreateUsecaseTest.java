package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.domain.service.DishIngredientService;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class DishCreateUsecaseTest {
    private DishCreateUsecase underTest;

    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishIngredientService dishIngredientService = mock(DishIngredientService.class);
    private final DishMapper dishMapper = mock(DishMapper.class);

    @BeforeEach
    void setUp() {
        underTest = new DishCreateUsecase(dishRepository, dishIngredientService, dishMapper);
    }

    @Test
    void shouldCreateDish() {
        // given
        var restaurantId = UUID.randomUUID();
        var dto = DishFixture.emptyManageDTO();
        var mappedDish = DishFixture.emptyEntity();
        var savedDish = DishFixture.withIdEntity(UUID.randomUUID());

        when(dishMapper.mapToSaveEntity(dto, restaurantId)).thenReturn(mappedDish);
        when(dishRepository.save(mappedDish)).thenReturn(savedDish);

        // when
        var result = underTest.create(dto, restaurantId);

        // then
        verify(dishMapper).mapToSaveEntity(dto, restaurantId);
        verify(dishIngredientService).replaceIngredients(eq(mappedDish), any());
        verify(dishRepository).save(mappedDish);

        assertThat(result).isEqualTo(savedDish);
    }
}