package com.guciowons.yummify.file.application;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.file.application.model.mapper.FileCommandMapper;
import com.guciowons.yummify.file.application.usecase.CreateFileUsecase;
import com.guciowons.yummify.file.application.usecase.DeleteFileUsecase;
import com.guciowons.yummify.file.application.usecase.GetFileUrlUsecase;
import com.guciowons.yummify.file.application.usecase.UpdateFileUsecase;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.entity.value.FileUrl;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.function.Supplier;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.*;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.*;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FileFacadeTest {
    private final CreateFileUsecase createFileUsecase = mock(CreateFileUsecase.class);
    private final UpdateFileUsecase updateFileUsecase = mock(UpdateFileUsecase.class);
    private final DeleteFileUsecase deleteFileUsecase = mock(DeleteFileUsecase.class);
    private final GetFileUrlUsecase getFileUrlUsecase = mock(GetFileUrlUsecase.class);
    private final DomainExceptionHandler fileDomainExceptionHandler = mock(DomainExceptionHandler.class);
    private final FileCommandMapper fileCommandMapper = mock(FileCommandMapper.class);

    private final FileFacade underTest = new FileFacade(
            createFileUsecase,
            updateFileUsecase,
            deleteFileUsecase,
            getFileUrlUsecase,
            fileDomainExceptionHandler,
            fileCommandMapper
    );

    @Test
    void shouldCreateFile() {
        // given
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1);
        var command = givenCreateFileCommand();
        var file = givenFile(1);

        when(fileCommandMapper.toCreateFileCommand(eq(directory), any(), eq(restaurantId))).thenReturn(command);
        when(createFileUsecase.create(command)).thenReturn(file);
        when(fileDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<File>>any()))
                .thenAnswer(inv -> inv.<Supplier<Restaurant>>getArgument(0).get());

        // when
        var result = underTest.create(directory, multipartFile, restaurantId);

        // then
        verify(fileCommandMapper).toCreateFileCommand(eq(directory), any(), eq(restaurantId));
        verify(createFileUsecase).create(command);
        verify(fileDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<File>>any());

        assertThat(result).isEqualTo(file.getId().value());
    }

    @Test
    void shouldUpdateFile() {
        // given
        var id = givenFileId(1).value();
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1);
        var command = givenUpdateFileCommand();

        when(fileCommandMapper.toUpdateFileCommand(eq(id), eq(directory), any(), eq(restaurantId))).thenReturn(command);
        doAnswer(inv -> {
            inv.<Runnable>getArgument(0).run();
            return null;
        }).when(fileDomainExceptionHandler).handle(any(Runnable.class));

        // when
        underTest.update(id, directory, multipartFile, restaurantId);

        // then
        verify(fileCommandMapper).toUpdateFileCommand(eq(id), eq(directory), any(), eq(restaurantId));
        verify(updateFileUsecase).update(command);
        verify(fileDomainExceptionHandler).handle(any(Runnable.class));
    }

    @Test
    void shouldDeleteFile() {
        // given
        var id = givenFileId(1).value();
        var restaurantId = givenRestaurantId(1);
        var command = givenDeleteFileCommand();

        when(fileCommandMapper.toDeleteFileCommand(id, restaurantId)).thenReturn(command);
        doAnswer(inv -> {
            inv.<Runnable>getArgument(0).run();
            return null;
        }).when(fileDomainExceptionHandler).handle(any(Runnable.class));

        // when
        underTest.delete(id, restaurantId);

        // then
        verify(fileCommandMapper).toDeleteFileCommand(id, restaurantId);
        verify(deleteFileUsecase).delete(command);
        verify(fileDomainExceptionHandler).handle(any(Runnable.class));
    }

    @Test
    void shouldGetFileUrl() throws MalformedURLException {
        // given
        var id = givenFileId(1).value();
        var restaurantId = givenRestaurantId(1);
        var command = givenGetFileUrlCommand();
        var fileUrl = givenFileUrl(1);

        when(fileCommandMapper.toGetFileUrlCommand(id, restaurantId)).thenReturn(command);
        when(getFileUrlUsecase.getPresignedUrl(command)).thenReturn(fileUrl);
        when(fileDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<FileUrl>>any()))
                .thenAnswer(inv -> inv.<Supplier<Restaurant>>getArgument(0).get());

        // when
        var result = underTest.getUrl(id, restaurantId);

        // then
        verify(fileCommandMapper).toGetFileUrlCommand(id, restaurantId);
        verify(getFileUrlUsecase).getPresignedUrl(command);
        verify(fileDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<FileUrl>>any());

        assertThat(result).isEqualTo(fileUrl.value());
    }

    @Test
    void shouldNotCreateFile_whenInputStreamThrowsException() throws IOException {
        // given
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1);

        when(multipartFile.getInputStream()).thenThrow(IOException.class);
        when(fileDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<File>>any()))
                .thenAnswer(inv -> inv.<Supplier<Restaurant>>getArgument(0).get());

        // when
        assertThatThrownBy(() -> underTest.create(directory, multipartFile, restaurantId))
                .isInstanceOf(CannotGetFileException.class);

        // then
        verify(fileCommandMapper, never()).toCreateFileCommand(any(), any(), any());
        verify(createFileUsecase, never()).create(any());
        verify(fileDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<File>>any());
    }

    @Test
    void shouldNotUpdateFile_whenInputStreamThrowsException() throws IOException {
        // given
        var id = givenFileId(1).value();
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1);

        when(multipartFile.getInputStream()).thenThrow(IOException.class);
        doAnswer(inv -> {
            inv.<Runnable>getArgument(0).run();
            return null;
        }).when(fileDomainExceptionHandler).handle(any(Runnable.class));

        // when
        assertThatThrownBy(() -> underTest.update(id, directory, multipartFile, restaurantId))
                .isInstanceOf(CannotGetFileException.class);

        // then
        verify(fileCommandMapper, never()).toUpdateFileCommand(any(), any(), any(), any());
        verify(updateFileUsecase, never()).update(any());
        verify(fileDomainExceptionHandler).handle(any(Runnable.class));
    }
}
