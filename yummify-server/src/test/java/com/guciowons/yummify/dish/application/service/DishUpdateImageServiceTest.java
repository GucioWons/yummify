package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.file.FileFacadePort;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishImageId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishUpdateImageServiceTest {
    private final FileFacadePort fileFacadePort = mock(FileFacadePort.class);

    private final DishUpdateImageService underTest = new DishUpdateImageService(fileFacadePort);

    @Test
    void shouldDeleteImage_WhenImageIsEmptyAndDishHasImage() {
        // given
        var dish = givenDish(1);
        var imageId = givenDishImageId(1);
        var image = mock(MultipartFile.class);
        dish.changeImage(imageId);

        when(image.isEmpty()).thenReturn(true);

        // when
        underTest.updateImage(dish, image);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort).delete(imageId.value(), dish.getRestaurantId());

        assertThat(dish.getImageId()).isNull();
    }

    @Test
    void shouldDoNothing_WhenImageIsEmptyAndDishHasNoImage() {
        // given
        var dish = givenDish(1);
        var image = mock(MultipartFile.class);

        when(image.isEmpty()).thenReturn(true);

        // when
        underTest.updateImage(dish, image);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort, never()).delete(any(), any());

        assertThat(dish.getImageId()).isNull();
    }

    @Test
    void shouldCreateImage_WhenImageIsNotEmptyAndDishHasNoImage() {
        // given
        var dish = givenDish(1);
        var image = mock(MultipartFile.class);
        var imageId = givenDishImageId(1).value();

        when(image.isEmpty()).thenReturn(false);
        when(fileFacadePort.create("dish", image, dish.getRestaurantId())).thenReturn(imageId);

        // when
        underTest.updateImage(dish, image);

        // then
        verify(fileFacadePort).create("dish", image, dish.getRestaurantId());
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort, never()).delete(any(), any());

        assertThat(dish.getImageId().value()).isEqualTo(imageId);
    }

    @Test
    void shouldUpdateImage_WhenImageIsNotEmptyAndDishHasImage() {
        // given
        var dish = givenDish(1);
        var imageId = givenDishImageId(1);
        var image = mock(MultipartFile.class);
        dish.changeImage(imageId);

        when(image.isEmpty()).thenReturn(false);

        // when
        underTest.updateImage(dish, image);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort).update(imageId.value(), "dish", image, dish.getRestaurantId());
        verify(fileFacadePort, never()).delete(any(), any());

        assertThat(dish.getImageId()).isEqualTo(imageId);
    }
}