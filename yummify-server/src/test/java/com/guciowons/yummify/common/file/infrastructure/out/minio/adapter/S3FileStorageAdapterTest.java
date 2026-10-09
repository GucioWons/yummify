package com.guciowons.yummify.common.file.infrastructure.out.minio.adapter;

import com.guciowons.yummify.common.file.infrastructure.framework.FileProperties;
import com.guciowons.yummify.common.file.infrastructure.out.s3.S3FileStorageAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFileStorageKey;
import static com.guciowons.yummify.common.file.infrastructure.fixture.FileInfrastructureFixture.givenBucketName;
import static com.guciowons.yummify.common.file.infrastructure.fixture.FileInfrastructureFixture.givenMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class S3FileStorageAdapterTest {
    private final S3Client s3Client = mock(S3Client.class);
    private final FileProperties fileProperties = mock(FileProperties.class);

    private final S3FileStorageAdapter underTest = new S3FileStorageAdapter(s3Client, fileProperties);

    @Test
    void shouldStoreFile() throws IOException {
        // given
        var storageKey = givenFileStorageKey(1);
        var bucketName = givenBucketName();
        var file = givenMultipartFile();

        when(fileProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.store(storageKey, file);

        // then
        verify(fileProperties).bucketName();

        ArgumentCaptor<PutObjectRequest> requestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client).putObject(requestCaptor.capture(), any(RequestBody.class));
        var putObjectRequest = requestCaptor.getValue();
        assertThat(putObjectRequest.bucket()).isEqualTo(bucketName);
        assertThat(putObjectRequest.key()).isEqualTo(storageKey.value());
        assertThat(putObjectRequest.contentType()).isEqualTo(file.getContentType());

        verify(file.getInputStream()).close();
    }

    @Test
    void shouldRemoveFile() {
        // given
        var storageKey = givenFileStorageKey(1);
        var bucketName = givenBucketName();

        when(fileProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.remove(storageKey);

        // then
        verify(fileProperties).bucketName();

        ArgumentCaptor<DeleteObjectRequest> captor = ArgumentCaptor.forClass(DeleteObjectRequest.class);
        verify(s3Client).deleteObject(captor.capture());
        DeleteObjectRequest deleteObjectRequest = captor.getValue();
        assertThat(deleteObjectRequest.bucket()).isEqualTo(bucketName);
        assertThat(deleteObjectRequest.key()).isEqualTo(storageKey.value());
    }

    @Test
    void shouldThrowException_WhenClosingInputStreamFails() throws IOException {
        // given
        var storageKey = givenFileStorageKey(1);
        var bucketName = givenBucketName();
        var file = givenMultipartFile();

        when(fileProperties.bucketName()).thenReturn(bucketName);
        doThrow(IOException.class).when(file.getInputStream()).close();

        // when + then
        assertThatThrownBy(() -> underTest.store(storageKey, file)).isInstanceOf(RuntimeException.class);
    }
}