package com.guciowons.yummify.file.domain.port;

public interface FilePresignedUrlPort {
    String getPresignedUrl(String storageKey);
}
