package com.guciowons.yummify.file.logic;

import com.guciowons.yummify.common.properties.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.InputStream;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final MinioProperties minioProperties;

    public void putFile(String storageKey, String contentType, InputStream inputStream, Long size) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey)
                .contentType(contentType)
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(inputStream, size));
    }

    public void deleteFile(String storageKey) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey)
                .build();

        s3Client.deleteObject(request);
    }

    public String getPresignedUrl(String storageKey) {
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
