package com.guciowons.yummify.common.file.infrastructure.framework;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileConfig {
    @Bean
    public S3Client s3Client(FileProperties fileProperties) {
        return S3Client.builder()
                .endpointOverride(URI.create(fileProperties.url()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(fileProperties.accessKey(), fileProperties.secretKey())
                ))
                .region(Region.US_EAST_1)
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .build();
    }

    @Bean
    public S3Presigner s3Presigner(FileProperties fileProperties) {
        return S3Presigner.builder()
                .endpointOverride(URI.create(fileProperties.url()))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(fileProperties.accessKey(), fileProperties.secretKey())))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .build();
    }
}
