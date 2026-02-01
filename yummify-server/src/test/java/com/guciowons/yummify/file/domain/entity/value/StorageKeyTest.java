package com.guciowons.yummify.file.domain.entity.value;

import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.common.security.aspect.UuidConditions.validUuid;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenDirectory;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFilename;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StorageKeyTest {
    @Test
    void shouldCreateStorageKey() {
        // given
        var filename = givenFilename(1);
        var restaurantId = givenRestaurantId(1);
        var directory = givenDirectory(1);

        var prefix = "%s/%s/".formatted(restaurantId.value(), directory.value());
        var suffix = "-%s".formatted(filename.value());

        // when
        var result = StorageKey.of(filename, restaurantId, directory);

        // then
        assertThat(result.value()).startsWith(prefix).endsWith(suffix);

        var uuidPart = result.value().replace(prefix, "").replace(suffix, "");

        assertThat(uuidPart).is(validUuid());
    }

    @Test
    void shouldThrowException_WhenStorageKeyIsNull() {
        // when + then
        assertThatThrownBy(() -> new StorageKey(null)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyIsEmpty() {
        // given
        var value = "";

        // when + then
        assertThatThrownBy(() -> new StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyStartsWithSlash() {
        // given
        var value = "/storage";

        // when + then
        assertThatThrownBy(() -> new StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyContainsDots() {
        // given
        var value = "../storage";

        // when + then
        assertThatThrownBy(() -> new StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }
}
