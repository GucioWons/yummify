package com.guciowons.yummify.file.infrastructure.adapter;

import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import com.guciowons.yummify.file.domain.port.FilePresignedUrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class FilePresignedUrlAdapter implements FilePresignedUrlPort {
    private final S3Presigner s3Presigner;
    private final MinioProperties minioProperties;

    @Override
    public String getUrl(String storageKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(1))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }
}
