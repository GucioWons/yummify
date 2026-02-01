package com.guciowons.yummify.file.infrastructure.adapter;

import com.guciowons.yummify.file.infrastructure.framework.MinioProperties;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.MalformedURLException;
import java.time.Duration;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFileUrl;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenStorageKey;
import static com.guciowons.yummify.file.infrastructure.fixture.FileInfrastructureFixture.givenBucketName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FileUrlProviderAdapterTest {
    private final S3Presigner s3Presigner = mock(S3Presigner.class);
    private final MinioProperties minioProperties = mock(MinioProperties.class);

    private final FileUrlProviderAdapter underTest = new FileUrlProviderAdapter(s3Presigner, minioProperties);

    @Test
    void shouldGetPresignedFileUrl() throws MalformedURLException {
        // given
        var storageKey = givenStorageKey(1);
        var bucketName = givenBucketName();
        var presignedRequest = mock(PresignedGetObjectRequest.class);
        var expectedUrl = givenFileUrl(1).value();

        when(minioProperties.bucketName()).thenReturn(bucketName);
        when(presignedRequest.url()).thenReturn(expectedUrl);
        when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenReturn(presignedRequest);

        // when
        var result = underTest.getUrl(storageKey);

        // then
        verify(minioProperties).bucketName();

        ArgumentCaptor<GetObjectPresignRequest> captor = ArgumentCaptor.forClass(GetObjectPresignRequest.class);
        verify(s3Presigner).presignGetObject(captor.capture());
        var getObjectPresignRequest = captor.getValue();

        assertThat(getObjectPresignRequest.signatureDuration()).isEqualTo(Duration.ofDays(1));
        assertThat(getObjectPresignRequest.getObjectRequest().bucket()).isEqualTo(bucketName);
        assertThat(getObjectPresignRequest.getObjectRequest().key()).isEqualTo(storageKey.value());

        assertThat(result.value()).isEqualTo(expectedUrl);
    }
}