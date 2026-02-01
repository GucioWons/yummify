package com.guciowons.yummify.file.infrastructure.adapter;

import com.guciowons.yummify.file.domain.entity.value.FileUrl;
import com.guciowons.yummify.file.domain.entity.value.StorageKey;
import com.guciowons.yummify.file.domain.port.out.FileUrlProviderPort;
import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class FileUrlProviderAdapter implements FileUrlProviderPort {
    private final S3Presigner s3Presigner;
    private final MinioProperties minioProperties;

    @Override
    public FileUrl getUrl(StorageKey storageKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey.value())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(1))
                .getObjectRequest(getObjectRequest)
                .build();

        return FileUrl.of(s3Presigner.presignGetObject(presignRequest).url());
    }
}
