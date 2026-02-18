package com.guciowons.yummify.file.domain.entity;

import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.common.security.aspect.UuidConditions.validUuid;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @Test
    void shouldCreateFileWithRandomId() {
        // given
        var restaurantId = givenFileRestaurantId(1);
        var storageKey = givenFileStorageKey(1);

        // when
        var result = File.create(restaurantId, storageKey);

        assertThat(result).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getStorageKey()).isEqualTo(storageKey);
    }

    @Test
    void shouldChangeStorageKey() {
        // given
        var file = givenFile(1);
        var newStorageKey = givenFileStorageKey(2);

        // when
        file.changeStorageKey(newStorageKey);

        // then
        assertThat(file.getStorageKey()).isEqualTo(newStorageKey);
    }

    @Test
    void shouldCreateStorageKey() {
        // given
        var filename = givenFilename(1);
        var restaurantId = givenFileRestaurantId(1);
        var directory = givenDirectory(1);

        var prefix = "%s/%s/".formatted(restaurantId.value(), directory.value());
        var suffix = "-%s".formatted(filename.value());

        // when
        var result = File.StorageKey.of(filename, restaurantId, directory);

        // then
        assertThat(result.value()).startsWith(prefix).endsWith(suffix);

        var uuidPart = result.value().replace(prefix, "").replace(suffix, "");

        assertThat(uuidPart).is(validUuid());
    }

    @Test
    void shouldGenerateUniqueStorageKeys() {
        // given
        var filename = givenFilename(1);
        var restaurantId = givenFileRestaurantId(1);
        var directory = givenDirectory(1);

        // when
        var firstResult = File.StorageKey.of(filename, restaurantId, directory);
        var secondResult = File.StorageKey.of(filename, restaurantId, directory);

        // then
        assertThat(firstResult).isNotEqualTo(secondResult);
    }

    @Test
    void shouldThrowException_WhenStorageKeyIsNull() {
        // when + then
        assertThatThrownBy(() -> new File.StorageKey(null)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyIsEmpty() {
        // given
        var value = "";

        // when + then
        assertThatThrownBy(() -> new File.StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyStartsWithSlash() {
        // given
        var value = "/storage";

        // when + then
        assertThatThrownBy(() -> new File.StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }

    @Test
    void shouldThrowException_WhenStorageKeyContainsDots() {
        // given
        var value = "../storage";

        // when + then
        assertThatThrownBy(() -> new File.StorageKey(value)).isInstanceOf(InvalidStorageKeyException.class);
    }
}
