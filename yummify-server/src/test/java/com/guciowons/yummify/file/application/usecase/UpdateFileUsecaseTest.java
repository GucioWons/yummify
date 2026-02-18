package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.givenUpdateFileCommand;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UpdateFileUsecaseTest {
    private final FileLookupService fileLookupService = mock(FileLookupService.class);
    private final FileRepository fileRepository = mock(FileRepository.class);
    private final FileStoragePort fileStoragePort = mock(FileStoragePort.class);

    private final UpdateFileUsecase underTest = new UpdateFileUsecase(fileLookupService, fileRepository, fileStoragePort);

    @Test
    void shouldUpdateFile() {
        // given
        var command = givenUpdateFileCommand();
        var file = givenFile(2);

        when(fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(file);

        // when
        underTest.update(command);

        // then
        verify(fileLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());

        var storeStorageKeyCaptor = ArgumentCaptor.forClass(File.StorageKey.class);
        verify(fileStoragePort).store(storeStorageKeyCaptor.capture(), eq(command.fileContent()));
        var storeStorageKey = storeStorageKeyCaptor.getValue();

        verify(fileRepository).save(file);

        var removeStorageKeyCaptor = ArgumentCaptor.forClass(File.StorageKey.class);
        verify(fileStoragePort).remove(removeStorageKeyCaptor.capture());
        var removeStorageKey = removeStorageKeyCaptor.getValue();

        assertThat(storeStorageKey).isNotEqualTo(removeStorageKey);
    }

    @Test
    void shouldRollbackUpdate_whenSaveThrowsException() {
        // given
        var command = givenUpdateFileCommand();
        var file = givenFile(2);

        when(fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(file);
        doThrow(RuntimeException.class).when(fileRepository).save(file);

        // when
        assertThatThrownBy(() -> underTest.update(command)).isInstanceOf(RuntimeException.class);

        // then
        verify(fileLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());

        var storeStorageKeyCaptor = ArgumentCaptor.forClass(File.StorageKey.class);
        verify(fileStoragePort).store(storeStorageKeyCaptor.capture(), eq(command.fileContent()));
        var storeStorageKey = storeStorageKeyCaptor.getValue();

        verify(fileRepository).save(file);

        var removeStorageKeyCaptor = ArgumentCaptor.forClass(File.StorageKey.class);
        verify(fileStoragePort).remove(removeStorageKeyCaptor.capture());
        var removeStorageKey = removeStorageKeyCaptor.getValue();

        assertThat(storeStorageKey).isEqualTo(removeStorageKey);
    }
}
