package com.guciowons.yummify.file.infrastructure.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileInfrastructureFixture {
    public static String givenBucketName() {
        return "test-bucket";
    }
}
