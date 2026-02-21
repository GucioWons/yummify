package com.guciowons.yummify.file.application;

import com.guciowons.yummify.file.application.model.mapper.FileCommandMapper;
import com.guciowons.yummify.file.application.usecase.CreateFileUsecase;
import com.guciowons.yummify.file.application.usecase.DeleteFileUsecase;
import com.guciowons.yummify.file.application.usecase.GetFileUrlUsecase;
import com.guciowons.yummify.file.application.usecase.UpdateFileUsecase;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.*;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.*;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FileFacadeTest {
    private final CreateFileUsecase createFileUsecase = mock(CreateFileUsecase.class);
    private final UpdateFileUsecase updateFileUsecase = mock(UpdateFileUsecase.class);
    private final DeleteFileUsecase deleteFileUsecase = mock(DeleteFileUsecase.class);
    private final GetFileUrlUsecase getFileUrlUsecase = mock(GetFileUrlUsecase.class);
    private final FileCommandMapper fileCommandMapper = mock(FileCommandMapper.class);

    private final FileFacade underTest = new FileFacade(
            createFileUsecase,
            updateFileUsecase,
            deleteFileUsecase,
            getFileUrlUsecase,
            fileCommandMapper
    );

    @Test
    void shouldCreateFile() {
        // given
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1).value();
        var command = givenCreateFileCommand();
        var file = givenFile(1);

        when(fileCommandMapper.toCreateFileCommand(eq(directory), any(), eq(restaurantId))).thenReturn(command);
        when(createFileUsecase.create(command)).thenReturn(file);

        // when
        var result = underTest.create(directory, multipartFile, restaurantId);

        // then
        verify(fileCommandMapper).toCreateFileCommand(eq(directory), any(), eq(restaurantId));
        verify(createFileUsecase).create(command);

        assertThat(result).isEqualTo(file.getId().value());
    }

    @Test
    void shouldUpdateFile() {
        // given
        var id = givenFileId(1).value();
        var directory = givenDirectory(1).value();
        var multipartFile = mock(MultipartFile.class);
        var restaurantId = givenRestaurantId(1).value();
        var command = givenUpdateFileCommand();

        when(fileCommandMapper.toUpdateFileCommand(eq(id), eq(directory), any(), eq(restaurantId))).thenReturn(command);

        // when
        underTest.update(id, directory, multipartFile, restaurantId);

        // then
        verify(fileCommandMapper).toUpdateFileCommand(eq(id), eq(directory), any(), eq(restaurantId));
        verify(updateFileUsecase).update(command);
    }

    @Test
    void shouldDeleteFile() {
        // given
        var id = givenFileId(1).value();
        var restaurantId = givenRestaurantId(1).value();
        var command = givenDeleteFileCommand();

        when(fileCommandMapper.toDeleteFileCommand(id, restaurantId)).thenReturn(command);

        // when
        underTest.delete(id, restaurantId);

        // then
        verify(fileCommandMapper).toDeleteFileCommand(id, restaurantId);
        verify(deleteFileUsecase).delete(command);
    }

    @Test
    void shouldGetFileUrl() throws MalformedURLException {
        // given
        var id = givenFileId(1).value();
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGetFileUrlCommand();
        var fileUrl = givenFileUrl(1);

        when(fileCommandMapper.toGetFileUrlCommand(id, restaurantId)).thenReturn(command);
        when(getFileUrlUsecase.getPresignedUrl(command)).thenReturn(fileUrl);

        // when
        var result = underTest.getUrl(id, restaurantId);

        // then
        verify(fileCommandMapper).toGetFileUrlCommand(id, restaurantId);
        verify(getFileUrlUsecase).getPresignedUrl(command);

        assertThat(result).isEqualTo(fileUrl.value());
    }
}
