package com.guciowons.yummify.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("minio")
public record MinioProperties(
        String minioUrl,
        String accessKey,
        String secretKey,
        String bucketName
) {
}
