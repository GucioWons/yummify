package com.guciowons.yummify.file.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.infrastructure.repository.FileRepository;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.infrastructure.service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    @InjectMocks
    private FileService underTest;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private FileRepository fileRepository;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

    @BeforeEach
    void setUp() {
        UserDTO mockUser = new UserDTO();
        mockUser.setId(UUID.randomUUID());
        mockUser.setRestaurantId(RESTAURANT_ID);
        mockUser.setUsername("testUser");

        RequestContext.set(new RequestContext(mockUser, LANGUAGE, LANGUAGE));
    }

    @Test
    public void shouldCreate() throws IOException {
        // given
        String directory = "test";
        UUID id = UUID.randomUUID();
        String storageKey = "testStorageKey";

        MultipartFile file = buildMultipartFile(false);
        File savedEntity = buildFile(id, storageKey);

        when(fileRepository.save(any())).thenReturn(savedEntity);

        // when
        underTest.create(directory, file);

        // then
        ArgumentCaptor<String> storageKeyCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileStorageService).putFile(storageKeyCaptor.capture(), anyString(), any(InputStream.class), anyLong());
        String capturedStorageKey = storageKeyCaptor.getValue();
        assertTrue(capturedStorageKey.startsWith(RESTAURANT_ID + "/" + directory + "/"));
        assertTrue(capturedStorageKey.endsWith("-filename"));

        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        verify(fileRepository).save(fileCaptor.capture());
        File capturedFile = fileCaptor.getValue();
        assertEquals(capturedStorageKey, capturedFile.getStorageKey());
        assertEquals(RESTAURANT_ID, capturedFile.getRestaurantId());
    }

    @Test
    public void shouldNotCreateWhenInputStreamThrowsException() throws IOException {
        // given
        String directory = "test";

        MultipartFile file = buildMultipartFile(true);

        // when
        assertThrows(CannotGetFileException.class, () -> underTest.create(directory, file));

        // then
        verify(fileStorageService, never()).putFile(any(), any(), any(), any());
    }

    @Test
    public void shouldUpdate() throws IOException {
        // given
        String directory = "test";
        String storageKey = "testStorageKey";
        UUID id = UUID.randomUUID();

        MultipartFile file = buildMultipartFile(false);
        File entity = buildFile(id, storageKey);

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.of(entity));

        // when
        underTest.update(id, directory, file);

        // then
        ArgumentCaptor<String> storageKeyCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileStorageService).putFile(storageKeyCaptor.capture(), anyString(), any(InputStream.class), anyLong());
        String capturedStorageKey = storageKeyCaptor.getValue();
        assertTrue(capturedStorageKey.startsWith(RESTAURANT_ID + "/" + directory + "/"));
        assertTrue(capturedStorageKey.endsWith("-filename"));

        verify(fileRepository).save(entity);
        verify(fileStorageService).deleteFile(storageKey);
    }

    @Test
    public void shouldNotUpdateWhenInputStreamThrowsException() throws IOException {
        // given
        String directory = "test";
        String storageKey = "testStorageKey";
        UUID id = UUID.randomUUID();

        MultipartFile file = buildMultipartFile(true);
        File entity = buildFile(id, storageKey);

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.of(entity));

        // when
        assertThrows(CannotGetFileException.class, () -> underTest.update(id, directory, file));

        // then
        verify(fileStorageService, never()).putFile(any(), any(), any(), any());
        verify(fileRepository, never()).save(any());
        verify(fileStorageService, never()).deleteFile(any());
    }

    @Test
    public void shouldRollbackUpdateWhenSaveThrowsException() throws IOException {
        // given
        String directory = "test";
        String storageKey = "testStorageKey";
        UUID id = UUID.randomUUID();

        MultipartFile file = buildMultipartFile(false);
        File entity = buildFile(id, storageKey);

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.of(entity));
        when(fileRepository.save(entity)).thenThrow(RuntimeException.class);

        // when
        assertThrows(RuntimeException.class, () -> underTest.update(id, directory, file));

        // then
        ArgumentCaptor<String> storageKeyCaptor = ArgumentCaptor.forClass(String.class);
        verify(fileStorageService).putFile(storageKeyCaptor.capture(), anyString(), any(InputStream.class), anyLong());
        String capturedStorageKey = storageKeyCaptor.getValue();
        assertTrue(capturedStorageKey.startsWith(RESTAURANT_ID + "/" + directory + "/"));
        assertTrue(capturedStorageKey.endsWith("-filename"));

        verify(fileStorageService).deleteFile(storageKeyCaptor.getValue());
        verify(fileStorageService, never()).deleteFile(storageKey);
    }

    @Test
    public void shouldDelete() {
        // given
        UUID id = UUID.randomUUID();
        String storageKey = "storage key";

        File file = buildFile(id, storageKey);

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.of(file));

        // when
        underTest.delete(id);

        // then
        verify(fileStorageService).deleteFile(storageKey);
        verify(fileRepository).delete(file);
    }

    @Test
    public void shouldNotDeleteAndThrowExceptionWhenNotFound() {
        // given
        UUID id = UUID.randomUUID();

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(FileNotFoundException.class, () -> underTest.delete(id));

        // then
        verify(fileStorageService, never()).deleteFile(any());
        verify(fileRepository, never()).delete(any());
    }

    @Test
    public void shouldGetPresignedUrl() {
        // given
        UUID id = UUID.randomUUID();
        String storageKey = "storage key";
        String presignedUrl = "presigned url";

        File file = buildFile(id, storageKey);

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.of(file));
        when(fileStorageService.getPresignedUrl(storageKey)).thenReturn(presignedUrl);

        // when
        String result = underTest.getPresignedUrl(id);

        // then
        assertEquals(presignedUrl, result);
    }

    @Test
    public void shouldNotGetPresignedUrlAndThrowExceptionWhenFileNotFound() {
        // given
        UUID id = UUID.randomUUID();

        when(fileRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(FileNotFoundException.class, () -> underTest.getPresignedUrl(id));

        // then
        verify(fileStorageService, never()).getPresignedUrl(any());
    }

    private File buildFile(UUID id, String storageKey) {
        File file = new File();
        file.setId(id);
        file.setStorageKey(storageKey);
        return file;
    }

    private MultipartFile buildMultipartFile(boolean shouldThrow) throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("filename");
        if (shouldThrow) {
            when(file.getInputStream()).thenThrow(IOException.class);
        } else {
            when(file.getInputStream()).thenReturn(InputStream.nullInputStream());
            when(file.getContentType()).thenReturn("contentType");
            when(file.getSize()).thenReturn(1024L);
        }
        return file;
    }
}
