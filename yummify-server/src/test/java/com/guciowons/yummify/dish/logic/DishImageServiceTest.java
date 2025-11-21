package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.file.PublicFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishImageServiceTest {
    @InjectMocks
    private DishImageService underTest;

    @Mock
    private DishService dishService;

    @Mock
    private PublicFileService publicFileService;

    @Mock
    private DishRepository dishRepository;

    @Test
    void shouldCreateDishImageIfOldImageIsEmpty() {
        // given
        UUID dishId = UUID.randomUUID();
        MultipartFile file = mock(MultipartFile.class);
        String imageUrl = "imageUrl";

        Dish entity = new Dish();

        when(dishService.getEntityById(dishId)).thenReturn(entity);
        when(publicFileService.getPresignedUrl())

        // when
        DishImageUrlDTO result = underTest.update(dishId, multipartFile);

        // then
        assertNull();
    }

    @Test
    void shouldUpdateDishImageIfOldImageIsNotEmpty() {
    }

    @Test
    void shouldDeleteDishImageIfOldImageIsNotEmpty() {
    }

    @Test
    void shouldDoNothingWhenNewAndOldImagesAreEmpty() {

    }
}