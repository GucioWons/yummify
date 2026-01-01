package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.dish.infractructure.dish.repository.DishRepository;
import com.guciowons.yummify.dish.application.dish.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import com.guciowons.yummify.file.PublicFileFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishImageServiceTest {
    @InjectMocks
    private DishImageService underTest;

    @Mock
    private DishService dishService;

    @Mock
    private PublicFileFacade publicFileFacade;

    @Mock
    private DishRepository dishRepository;

    private static final UUID DISH_ID = UUID.randomUUID();
    private static final UUID IMAGE_ID = UUID.randomUUID();

    @Test
    void shouldCreateDishImageIfOldImageIsEmpty() {
        // given
        Dish entity = buildDish(null);
        String imageUrl = "imageUrl";
        MultipartFile image = mockMultipartFile(false);

        when(dishService.getEntityById(DISH_ID)).thenReturn(entity);
        when(publicFileFacade.create("dish", image)).thenReturn(IMAGE_ID);
        when(publicFileFacade.getPresignedUrl(IMAGE_ID)).thenReturn(imageUrl);

        // when
        DishImageUrlDTO result = underTest.update(DISH_ID, image);

        // then
        verify(dishRepository).save(entity);

        assertEquals(imageUrl, result.imageUrl());
        assertEquals(IMAGE_ID, entity.getImageId());
    }

    @Test
    void shouldUpdateDishImageIfOldImageIsNotEmpty() {
        // given
        Dish entity = buildDish(IMAGE_ID);
        String imageUrl = "imageUrl";
        MultipartFile image = mockMultipartFile(false);

        when(dishService.getEntityById(DISH_ID)).thenReturn(entity);
        when(publicFileFacade.getPresignedUrl(IMAGE_ID)).thenReturn(imageUrl);

        // when
        DishImageUrlDTO result = underTest.update(DISH_ID, image);

        // then
        verify(publicFileFacade).update(IMAGE_ID, "dish", image);
        verify(dishRepository).save(entity);

        assertEquals(imageUrl, result.imageUrl());
        assertEquals(IMAGE_ID, entity.getImageId());
    }

    @Test
    void shouldDeleteDishImageIfOldImageIsNotEmpty() {
        // given
        Dish entity = buildDish(IMAGE_ID);
        MultipartFile image = mockMultipartFile(true);

        when(dishService.getEntityById(DISH_ID)).thenReturn(entity);

        // when
        DishImageUrlDTO result = underTest.update(DISH_ID, image);

        // then
        verify(publicFileFacade).delete(IMAGE_ID);
        verify(dishRepository).save(entity);

        assertNull(result.imageUrl());
        assertNull(entity.getImageId());
    }

    @Test
    void shouldDoNothingWhenNewAndOldImagesAreEmpty() {
        // given
        Dish entity = buildDish(null);
        MultipartFile image = mockMultipartFile(true);

        when(dishService.getEntityById(DISH_ID)).thenReturn(entity);

        // when
        DishImageUrlDTO result = underTest.update(DISH_ID, image);

        // then
        verify(dishRepository, never()).save(entity);

        assertNull(result.imageUrl());
        assertNull(entity.getImageId());
    }

    private Dish buildDish(UUID imageId) {
        Dish dish = new Dish();
        dish.setId(DISH_ID);
        dish.setImageId(imageId);
        return dish;
    }

    private MultipartFile mockMultipartFile(boolean isEmpty) {
        MultipartFile image = mock(MultipartFile.class);
        when(image.isEmpty()).thenReturn(isEmpty);
        return image;
    }
}