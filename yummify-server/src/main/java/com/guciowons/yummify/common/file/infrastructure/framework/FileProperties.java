package com.guciowons.yummify.common.file.infrastructure.framework;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("file")
public record FileProperties(
        String url,
        String accessKey,
        String secretKey,
        String bucketName
) {
}
