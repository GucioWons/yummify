package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.file.application.FileUrlProviderPort;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFile;
import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFileUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishImageUrlProviderTest {
    private final FileUrlProviderPort fileUrlProviderPort = mock(FileUrlProviderPort.class);

    private final DishImageUrlProvider underTest = new DishImageUrlProvider(fileUrlProviderPort);

    @Test
    void shouldGetDishImageUrl() throws MalformedURLException {
        // given
        var image = givenFile(1);
        var imageUrl = givenFileUrl(1);

        when(fileUrlProviderPort.getUrl(image.getStorageKey())).thenReturn(imageUrl);

        // when
        var result = underTest.get(image);

        // then
        verify(fileUrlProviderPort).getUrl(image.getStorageKey());

        assertThat(result).isEqualTo(imageUrl.toString());
    }

    @Test
    void shouldNotGetDishImageUrl_WhenImageIdIsNull() {
        // when
        var result = underTest.get(null);

        // then
        assertThat(result).isNull();
    }

}