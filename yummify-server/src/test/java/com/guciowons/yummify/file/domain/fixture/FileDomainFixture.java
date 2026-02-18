package com.guciowons.yummify.file.domain.fixture;

import com.guciowons.yummify.file.domain.model.Directory;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.model.FileUrl;
import com.guciowons.yummify.file.domain.model.Filename;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.UUID;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDomainFixture {
    public static File givenFile(int seed) {
        return File.of(givenRestaurantId(1), givenStorageKey(seed));
    }

    public static FileId givenFileId(int seed) {
        return FileId.of(UUID.nameUUIDFromBytes("file-%s".formatted(seed).getBytes()));
    }

    public static Filename givenFilename(int seed) {
        return Filename.of("filename-%s".formatted(seed));
    }

    public static Directory givenDirectory(int seed) {
        return Directory.of("directory-%s".formatted(seed));
    }

    public static StorageKey givenStorageKey(int seed) {
        return new StorageKey("storage-key-%s".formatted(seed));
    }

    public static FileUrl givenFileUrl(int seed) throws MalformedURLException {
        return FileUrl.of(URI.create("https://minio.test/file-%s.pdf".formatted(seed)).toURL());
    }
}
