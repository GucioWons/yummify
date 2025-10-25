package com.guciowons.yummify.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("minio")
public record MinioProperties(
        String minioUrl,
        String accessKey,
        String secretKey
) {
}
