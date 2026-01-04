package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.dish.fixture.DishFixture;
import com.guciowons.yummify.file.exposed.FileFacadePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishUpdateImageServiceTest {
    private DishUpdateImageService underTest;

    private final FileFacadePort fileFacadePort = mock(FileFacadePort.class);

    @BeforeEach
    void setUp() {
        underTest = new DishUpdateImageService(fileFacadePort);
    }

    @Test
    void shouldCreateImage_WhenFileIsNotEmptyAndImageIdIsNull() {
        // given
        var restaurantId = UUID.randomUUID();
        var imageId = UUID.randomUUID();
        var dish = DishFixture.emptyEntity();
        dish.setRestaurantId(restaurantId);
        var multipartFile = mockMultipartFile(false);

        when(fileFacadePort.create("dish", multipartFile, restaurantId)).thenReturn(imageId);

        // when
        underTest.updateImage(dish, multipartFile);

        // then
        verify(fileFacadePort).create("dish", multipartFile, restaurantId);
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort, never()).delete(any(), any());

        assertThat(dish.getImageId()).isEqualTo(imageId);
    }

    @Test
    void shouldUpdateImage_WhenFileIsNotEmptyAndImageIdIsNotNull() {
        // given
        var restaurantId = UUID.randomUUID();
        var imageId = UUID.randomUUID();
        var dish = DishFixture.emptyEntity();
        dish.setRestaurantId(restaurantId);
        dish.setImageId(imageId);
        var multipartFile = mockMultipartFile(false);

        // when
        underTest.updateImage(dish, multipartFile);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort).update(imageId, "dish", multipartFile, restaurantId);
        verify(fileFacadePort, never()).delete(any(), any());

        assertThat(dish.getImageId()).isEqualTo(imageId);
    }

    @Test
    void shouldDeleteImage_WhenFileIsEmptyAndImageIdIsNotNull() {
        // given
        var restaurantId = UUID.randomUUID();
        var imageId = UUID.randomUUID();
        var dish = DishFixture.emptyEntity();
        dish.setRestaurantId(restaurantId);
        dish.setImageId(imageId);
        var multipartFile = mockMultipartFile(true);

        // when
        underTest.updateImage(dish, multipartFile);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort).delete(imageId, restaurantId);

        assertThat(dish.getImageId()).isNull();
    }

    @Test
    void shouldDoNothing_WhenFileIsEmptyAndImageIdIsNull() {
        // given
        var restaurantId = UUID.randomUUID();
        var dish = DishFixture.emptyEntity();
        dish.setRestaurantId(restaurantId);
        var multipartFile = mockMultipartFile(true);

        // when
        underTest.updateImage(dish, multipartFile);

        // then
        verify(fileFacadePort, never()).create(any(), any(), any());
        verify(fileFacadePort, never()).update(any(), any(), any(), any());
        verify(fileFacadePort, never()).delete(any(), any());
    }

    private MultipartFile mockMultipartFile(boolean isEmpty) {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.isEmpty()).thenReturn(isEmpty);
        return multipartFile;
    }
}