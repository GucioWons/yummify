package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.dish.application.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.application.service.DishImageUrlProvider;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishFacadeTest {
    private DishFacade underTest;

    private final DishCreateUsecase dishCreateUsecase = mock(DishCreateUsecase.class);
    private final DishGetAllUsecase dishGetAllUsecase = mock(DishGetAllUsecase.class);
    private final DishGetUsecase dishGetUsecase = mock(DishGetUsecase.class);
    private final DishUpdateUsecase dishUpdateUsecase = mock(DishUpdateUsecase.class);
    private final DishUpdateImageUsecase dishUpdateImageUsecase = mock(DishUpdateImageUsecase.class);
    private final DishImageUrlProvider dishImageUrlProvider = mock(DishImageUrlProvider.class);
    private final DishMapper dishMapper = mock(DishMapper.class);
    
    @BeforeEach
    void setUp() {
        underTest = new DishFacade(
                dishCreateUsecase, 
                dishGetAllUsecase, 
                dishGetUsecase, 
                dishUpdateUsecase, 
                dishUpdateImageUsecase, 
                dishImageUrlProvider, 
                dishMapper
        );
    }

    @Test
    void shouldCreateDish() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dto = DishFixture.emptyManageDTO();
        var dish = DishFixture.withIdEntity(dishId);
        var expectedResult = DishFixture.withIdManageDTO(dishId);

        when(dishCreateUsecase.create(dto, restaurantId)).thenReturn(dish);
        when(dishMapper.mapToManageDTO(eq(dish), any()))
                .thenReturn(expectedResult);

        // when
        var result = underTest.create(dto, restaurantId);

        // then
        verify(dishCreateUsecase).create(dto, restaurantId);
        verify(dishMapper).mapToManageDTO(eq(dish), any());

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldGetAllDishes() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dish = DishFixture.withIdEntity(dishId);
        var dishes = List.of(dish);
        var dto = DishFixture.withIdClientDTO(dishId);
        var expectedResult = List.of(dto);

        when(dishGetAllUsecase.getAll(restaurantId)).thenReturn(dishes);
        when(dishMapper.mapToClientDTO(eq(dish), any()))
                .thenReturn(dto);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(dishGetAllUsecase).getAll(restaurantId);
        verify(dishMapper).mapToClientDTO(eq(dish), any());

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldGetManageDTO() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dish = DishFixture.withIdEntity(dishId);
        var expectedResult = DishFixture.withIdManageDTO(dishId);

        when(dishGetUsecase.getById(dishId, restaurantId)).thenReturn(dish);
        when(dishMapper.mapToManageDTO(eq(dish), any()))
                .thenReturn(expectedResult);
        // when
        var result = underTest.getManageDTO(dishId, restaurantId);

        // then
        verify(dishGetUsecase).getById(dishId, restaurantId);
        verify(dishMapper).mapToManageDTO(eq(dish), any());

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldUpdateDish() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var dto = DishFixture.withIdManageDTO(dishId);
        var dish = DishFixture.withIdEntity(dishId);
        var expectedResult = DishFixture.withIdManageDTO(dishId);

        when(dishUpdateUsecase.update(dishId, dto, restaurantId)).thenReturn(dish);
        when(dishMapper.mapToManageDTO(eq(dish), any()))
                .thenReturn(expectedResult);

        // when
        var result = underTest.update(dishId, dto, restaurantId);

        // then
        verify(dishUpdateUsecase).update(dishId, dto, restaurantId);
        verify(dishMapper).mapToManageDTO(eq(dish), any());

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldUpdateImage() {
        // given
        var restaurantId = UUID.randomUUID();
        var dishId = UUID.randomUUID();
        var multipartFile = mock(MultipartFile.class);
        var imageId = UUID.randomUUID();
        var imageUrl = "url";

        when(dishUpdateImageUsecase.updateImage(dishId, multipartFile, restaurantId)).thenReturn(imageId);
        when(dishImageUrlProvider.get(imageId, restaurantId)).thenReturn(imageUrl);

        // when
        var result = underTest.updateImage(dishId, multipartFile, restaurantId);

        // then
        verify(dishUpdateImageUsecase).updateImage(dishId, multipartFile, restaurantId);
        verify(dishImageUrlProvider).get(imageId, restaurantId);

        assertThat(result.imageUrl()).isEqualTo(imageUrl);
    }
}