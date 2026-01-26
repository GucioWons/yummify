package com.guciowons.yummify.file.infrastructure.adapter;

import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.givenFileContent;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenStorageKey;
import static com.guciowons.yummify.file.infrastructure.fixture.FileInfrastructureFixture.givenBucketName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FileStorageAdapterTest {
    private final S3Client s3Client = mock(S3Client.class);
    private final MinioProperties minioProperties = mock(MinioProperties.class);

    private final FileStorageAdapter underTest = new FileStorageAdapter(s3Client, minioProperties);

    @Test
    void shouldStoreFile() throws IOException {
        // given
        var storageKey = givenStorageKey(1);
        var bucketName = givenBucketName();
        var fileContent = givenFileContent(1);

        when(minioProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.store(storageKey, fileContent);

        // then
        verify(minioProperties).bucketName();

        ArgumentCaptor<PutObjectRequest> requestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client).putObject(requestCaptor.capture(), any(RequestBody.class));
        var putObjectRequest = requestCaptor.getValue();
        assertThat(putObjectRequest.bucket()).isEqualTo(bucketName);
        assertThat(putObjectRequest.key()).isEqualTo(storageKey.value());
        assertThat(putObjectRequest.contentType()).isEqualTo(fileContent.contentType());

        verify(fileContent.inputStream()).close();
    }

    @Test
    void shouldRemoveFile() {
        // given
        var storageKey = givenStorageKey(1);
        var bucketName = givenBucketName();

        when(minioProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.remove(storageKey);

        // then
        verify(minioProperties).bucketName();

        ArgumentCaptor<DeleteObjectRequest> captor = ArgumentCaptor.forClass(DeleteObjectRequest.class);
        verify(s3Client).deleteObject(captor.capture());
        DeleteObjectRequest deleteObjectRequest = captor.getValue();
        assertThat(deleteObjectRequest.bucket()).isEqualTo(bucketName);
        assertThat(deleteObjectRequest.key()).isEqualTo(storageKey.value());
    }

    @Test
    void shouldThrowException_WhenClosingInputStreamFails() throws IOException {
        // given
        var storageKey = givenStorageKey(1);
        var bucketName = givenBucketName();
        var fileContent = givenFileContent(1);

        when(minioProperties.bucketName()).thenReturn(bucketName);
        doThrow(IOException.class).when(fileContent.inputStream()).close();

        // when + then
        assertThatThrownBy(() -> underTest.store(storageKey, fileContent)).isInstanceOf(RuntimeException.class);
    }
}