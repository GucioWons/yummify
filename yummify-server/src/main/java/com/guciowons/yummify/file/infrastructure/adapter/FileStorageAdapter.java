package com.guciowons.yummify.file.infrastructure.adapter;

import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import com.guciowons.yummify.file.domain.model.FileContent;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
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
public class FileStorageAdapter implements FileStoragePort {
    private final S3Client s3Client;
    private final MinioProperties minioProperties;

    @Override
    public void upload(String storageKey, FileContent fileContent) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey)
                .contentType(fileContent.contentType())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(fileContent.inputStream(), fileContent.size()));
        closeInputStream(fileContent.inputStream());
    }

    @Override
    public void delete(String storageKey) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(minioProperties.bucketName())
                .key(storageKey)
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
