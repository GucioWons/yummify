package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishUpdateImageService;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.fixture.DishFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishUpdateImageUsecaseTest {
    private DishUpdateImageUsecase underTest;

    private final DishGetUsecase dishGetUsecase = mock(DishGetUsecase.class);
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishUpdateImageService dishUpdateImageService = mock(DishUpdateImageService.class);

    @BeforeEach
    void setUp() {
        underTest = new DishUpdateImageUsecase(dishGetUsecase, dishRepository, dishUpdateImageService);
    }

    @Test
    void shouldUpdateImage() {
        // given
        var dishId = UUID.randomUUID();
        var restaurantId = UUID.randomUUID();
        var multipartFile = mock(MultipartFile.class);
        var dish = DishFixture.withIdEntity(dishId);
        var imageId = UUID.randomUUID();
        var savedDish = DishFixture.withIdEntity(dishId);
        savedDish.setImageId(imageId);

        when(dishGetUsecase.getById(dishId, restaurantId)).thenReturn(dish);
        when(dishRepository.save(dish)).thenReturn(savedDish);

        // when
        var result = underTest.updateImage(dishId, multipartFile, restaurantId);

        // then
        verify(dishGetUsecase).getById(dishId, restaurantId);
        verify(dishUpdateImageService).updateImage(dish, multipartFile);
        verify(dishRepository).save(dish);

        assertThat(result).isEqualTo(imageId);
    }

}