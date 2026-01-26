package com.guciowons.yummify.file.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFile;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenStorageKey;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;

class FileTest {
    @Test
    void shouldCreateFileWithRandomId() {
        // given
        var restaurantId = givenRestaurantId(1);
        var storageKey = givenStorageKey(1);

        // when
        var result = File.of(restaurantId, storageKey);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getStorageKey()).isEqualTo(storageKey);
    }

    @Test
    void shouldUpdateStorageKey() {
        // given
        var file = givenFile(1);
        var newStorageKey = givenStorageKey(2);

        // when
        file.changeStorageKey(newStorageKey);

        // then
        assertThat(file.getStorageKey()).isEqualTo(newStorageKey);
    }
}
