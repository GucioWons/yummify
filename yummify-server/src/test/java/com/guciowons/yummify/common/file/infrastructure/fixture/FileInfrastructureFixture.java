package com.guciowons.yummify.common.file.infrastructure.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.mock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileInfrastructureFixture {
    public static String givenBucketName() {
        return "test-bucket";
    }

    public static MultipartFile givenMultipartFile() {
        return mock(MultipartFile.class);
    }
}
