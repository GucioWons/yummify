package com.guciowons.yummify.file.infrastructure.out.minio;

import com.guciowons.yummify.file.application.model.FileContent;
import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioFileStorageAdapter implements FileStoragePort {
    private final S3Client s3Client;
    private final MinioProperties minioProperties;

    @Override
    public void store(File.StorageKey storageKey, FileContent fileContent) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey.value())
                .contentType(fileContent.contentType())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(fileContent.inputStream(), fileContent.size()));
        closeInputStream(fileContent.inputStream());
    }

    @Override
    public void remove(File.StorageKey storageKey) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey.value())
                .build();

        s3Client.deleteObject(request);
    }

    private void closeInputStream(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
