package com.guciowons.yummify.common.file.infrastructure.out.s3;

import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.common.file.infrastructure.framework.FileProperties;
import com.guciowons.yummify.common.file.application.FileStoragePort;
import com.guciowons.yummify.common.file.domain.exception.CannotGetFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3FileStorageAdapter implements FileStoragePort {
    private final S3Client s3Client;
    private final FileProperties fileProperties;

    @Override
    public void store(File.StorageKey storageKey, MultipartFile file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(fileProperties.bucketName())
                .key(storageKey.value())
                .contentType(file.getContentType())
                .build();

        try {
            InputStream inputStream = file.getInputStream();
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, file.getSize()));
            closeInputStream(file.getInputStream());
        } catch (IOException e) {
            throw new CannotGetFileException();
        }
    }

    @Override
    public void remove(File.StorageKey storageKey) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(fileProperties.bucketName())
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
