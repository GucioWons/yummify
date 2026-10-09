package com.guciowons.yummify.common.file.infrastructure.out.minio.adapter;

import com.guciowons.yummify.common.file.infrastructure.framework.FileProperties;
import com.guciowons.yummify.common.file.infrastructure.out.s3.S3FileUrlProviderAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.MalformedURLException;
import java.time.Duration;

import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFileStorageKey;
import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFileUrl;
import static com.guciowons.yummify.common.file.infrastructure.fixture.FileInfrastructureFixture.givenBucketName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class S3FileUrlProviderAdapterTest {
    private final S3Presigner s3Presigner = mock(S3Presigner.class);
    private final FileProperties fileProperties = mock(FileProperties.class);

    private final S3FileUrlProviderAdapter underTest = new S3FileUrlProviderAdapter(s3Presigner, fileProperties);

    @Test
    void shouldGetPresignedFileUrl() throws MalformedURLException {
        // given
        var storageKey = givenFileStorageKey(1);
        var bucketName = givenBucketName();
        var presignedRequest = mock(PresignedGetObjectRequest.class);
        var expectedUrl = givenFileUrl(1);

        when(fileProperties.bucketName()).thenReturn(bucketName);
        when(presignedRequest.url()).thenReturn(expectedUrl);
        when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenReturn(presignedRequest);

        // when
        var result = underTest.getUrl(storageKey);

        // then
        verify(fileProperties).bucketName();

        ArgumentCaptor<GetObjectPresignRequest> captor = ArgumentCaptor.forClass(GetObjectPresignRequest.class);
        verify(s3Presigner).presignGetObject(captor.capture());
        var getObjectPresignRequest = captor.getValue();

        assertThat(getObjectPresignRequest.signatureDuration()).isEqualTo(Duration.ofDays(1));
        assertThat(getObjectPresignRequest.getObjectRequest().bucket()).isEqualTo(bucketName);
        assertThat(getObjectPresignRequest.getObjectRequest().key()).isEqualTo(storageKey.value());

        assertThat(result).isEqualTo(expectedUrl);
    }
}