package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.file.exposed.FileFacadePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishImageUrlProviderTest {
    private DishImageUrlProvider underTest;

    private final FileFacadePort fileFacadePort = mock(FileFacadePort.class);

    @BeforeEach
    void setUp() {
        underTest = new DishImageUrlProvider(fileFacadePort);
    }

    @Test
    void shouldGetDishImageUrl_WhenImageIdIsNotNull() {
        // given
        var restaurantId = UUID.randomUUID();
        var imageId = UUID.randomUUID();
        var imageUrl = "url";

        when(fileFacadePort.getUrl(imageId, restaurantId)).thenReturn(imageUrl);

        // when
        var result = underTest.get(imageId, restaurantId);

        // then
        verify(fileFacadePort).getUrl(imageId, restaurantId);

        assertThat(result).isEqualTo(imageUrl);
    }

    @Test
    void shouldNotGetDishImageUrl_WhenImageIdIsNull() {
        // given
        var restaurantId = UUID.randomUUID();

        // when
        var result = underTest.get(null, restaurantId);

        // then
        verify(fileFacadePort, never()).getUrl(any(), any());

        assertThat(result).isNull();
    }
}