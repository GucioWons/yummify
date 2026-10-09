package com.guciowons.yummify.common.file.infrastructure.out.s3;

import com.guciowons.yummify.common.file.application.FileUrlProviderPort;
import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.common.file.infrastructure.framework.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class S3FileUrlProviderAdapter implements FileUrlProviderPort {
    private final S3Presigner s3Presigner;
    private final FileProperties fileProperties;

    @Override
    public URL getUrl(File.StorageKey storageKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(fileProperties.bucketName())
                .key(storageKey.value())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(1))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url();
    }
}
