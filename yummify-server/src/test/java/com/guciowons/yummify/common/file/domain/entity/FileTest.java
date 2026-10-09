package com.guciowons.yummify.common.file.domain.entity;

import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.common.file.domain.exception.InvalidStorageKeyException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {
    @Test
    void shouldCreateFileWithRandomId() {
        // given
        var storageKey = givenFileStorageKey(1);

        // when
        var result = File.create(storageKey);

        assertThat(result).isNotNull();
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
