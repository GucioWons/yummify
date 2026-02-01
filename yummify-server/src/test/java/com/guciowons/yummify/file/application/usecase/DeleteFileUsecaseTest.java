package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.givenDeleteFileCommand;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFile;
import static org.mockito.Mockito.*;

class DeleteFileUsecaseTest {
    private final FileLookupService fileLookupService = mock(FileLookupService.class);
    private final FileRepository fileRepository = mock(FileRepository.class);
    private final FileStoragePort fileStoragePort = mock(FileStoragePort.class);

    private final DeleteFileUsecase underTest = new DeleteFileUsecase(
            fileLookupService,
            fileRepository,
            fileStoragePort
    );

    @Test
    void shouldDeleteFile() {
        // given
        var command = givenDeleteFileCommand();
        var file = givenFile(1);

        when(fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(file);

        // when
        underTest.delete(command);

        // then
        verify(fileLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(fileStoragePort).remove(file.getStorageKey());
        verify(fileRepository).delete(file);
    }
}
