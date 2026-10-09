package com.guciowons.yummify.common.file.domain.fixture;

import com.guciowons.yummify.common.file.domain.model.File;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDomainFixture {
    public static File givenFile(int seed) {
        return new File(givenFileId(1), givenFileStorageKey(seed));
    }

    public static File.Id givenFileId(int seed) {
        return File.Id.of(UUID.nameUUIDFromBytes("file-%s".formatted(seed).getBytes()));
    }

    public static File.StorageKey givenFileStorageKey(int seed) {
        return new File.StorageKey("storage-key-%s".formatted(seed));
    }

    public static URL givenFileUrl(int seed) throws MalformedURLException {
        return URI.create("https://minio.test/file-%s.pdf".formatted(seed)).toURL();
    }
}
