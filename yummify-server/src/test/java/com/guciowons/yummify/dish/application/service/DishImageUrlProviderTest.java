package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.file.FileFacadePort;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishImageId;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFileUrl;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishImageUrlProviderTest {
    private final FileFacadePort fileFacadePort = mock(FileFacadePort.class);

    private final DishImageUrlProvider underTest = new DishImageUrlProvider(fileFacadePort);

    @Test
    void shouldGetDishImageUrl() throws MalformedURLException {
        // given
        var imageId = givenDishImageId(1);
        var restaurantId = givenRestaurantId(1);
        var imageUrl = givenFileUrl(1).value();

        when(fileFacadePort.getUrl(imageId.value(), restaurantId)).thenReturn(imageUrl);

        // when
        var result = underTest.get(imageId, restaurantId);

        // then
        verify(fileFacadePort).getUrl(imageId.value(), restaurantId);

        assertThat(result).isEqualTo(imageUrl.toString());
    }

    @Test
    void shouldNotGetDishImageUrl_WhenImageIdIsNull() {
        // given
        var restaurantId = givenRestaurantId(1);

        // when
        var result = underTest.get(null, restaurantId);

        // then
        assertThat(result).isNull();
    }

}