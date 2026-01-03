package com.guciowons.yummify.file.domain.port;

public interface FilePresignedUrlPort {
    String getUrl(String storageKey);
}
