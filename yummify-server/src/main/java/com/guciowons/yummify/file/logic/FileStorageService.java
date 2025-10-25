package com.guciowons.yummify.file.logic;

import com.guciowons.yummify.file.config.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final S3Client s3Client;
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
}
