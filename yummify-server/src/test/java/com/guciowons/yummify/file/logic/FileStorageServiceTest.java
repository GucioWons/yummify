package com.guciowons.yummify.file.logic;

import com.guciowons.yummify.file.config.MinioProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileStorageServiceTest {
    @InjectMocks
    private FileStorageService underTest;

    @Mock
    private S3Client s3Client;

    @Mock
    private S3Presigner s3Presigner;

    @Mock
    private MinioProperties minioProperties;

    @Test
    public void shouldPutFile() {
        // given
        String storageKey = "testStorageKey";
        String contentType = "testContentType";
        InputStream inputStream = InputStream.nullInputStream();
        Long size = 1L;

        String bucketName = "testBucket";

        when(minioProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.putFile(storageKey, contentType, inputStream, size);

        //then
        ArgumentCaptor<PutObjectRequest> requestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        ArgumentCaptor<RequestBody> requestBodyCaptor = ArgumentCaptor.forClass(RequestBody.class);
        verify(s3Client).putObject(requestCaptor.capture(), requestBodyCaptor.capture());

        PutObjectRequest putObjectRequest = requestCaptor.getValue();
        assertEquals(bucketName, putObjectRequest.bucket());
        assertEquals(storageKey, putObjectRequest.key());
        assertEquals(contentType, putObjectRequest.contentType());

        RequestBody requestBody = requestBodyCaptor.getValue();
        assertEquals(size, requestBody.optionalContentLength().orElse(-1L));
    }

    @Test
    public void shouldDeleteFile() {
        // given
        String storageKey = "testStorageKey";
        String bucketName = "testBucket";

        when(minioProperties.bucketName()).thenReturn(bucketName);

        // when
        underTest.deleteFile(storageKey);

        // then
        ArgumentCaptor<DeleteObjectRequest> requestCaptor = ArgumentCaptor.forClass(DeleteObjectRequest.class);
        verify(s3Client).deleteObject(requestCaptor.capture());

        DeleteObjectRequest deleteObjectRequest = requestCaptor.getValue();
        assertEquals(bucketName, deleteObjectRequest.bucket());
        assertEquals(storageKey, deleteObjectRequest.key());
    }

    @Test
    public void shouldGetPresignedUrl() throws MalformedURLException {
        // given
        String storageKey = "testStorageKey";
        String bucketName = "testBucket";

        PresignedGetObjectRequest presignedGetObjectRequest = mock(PresignedGetObjectRequest.class);
        URL presignedUrl = URI.create("http://example.com/presigned").toURL();

        when(minioProperties.bucketName()).thenReturn(bucketName);
        when(s3Presigner.presignGetObject(any(GetObjectPresignRequest.class))).thenReturn(presignedGetObjectRequest);
        when(presignedGetObjectRequest.url()).thenReturn(presignedUrl);

        // when
        String result = underTest.getPresignedUrl(storageKey);

        // then
        ArgumentCaptor<GetObjectPresignRequest> requestCaptor = ArgumentCaptor.forClass(GetObjectPresignRequest.class);
        verify(s3Presigner).presignGetObject(requestCaptor.capture());

        GetObjectRequest getObjectRequest = requestCaptor.getValue().getObjectRequest();
        assertEquals(bucketName, getObjectRequest.bucket());
        assertEquals(storageKey, getObjectRequest.key());

        assertEquals(presignedUrl.toString(), result);
    }
}
