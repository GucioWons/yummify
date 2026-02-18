package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.givenCreateFileCommand;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateFileUsecaseTest {
    private final FileRepository fileRepository = mock(FileRepository.class);
    private final FileStoragePort fileStoragePort = mock(FileStoragePort.class);

    private final CreateFileUsecase underTest = new CreateFileUsecase(fileRepository, fileStoragePort);

    @Test
    void shouldCreateFile() {
        // given
        var command = givenCreateFileCommand();

        // when
        var result = underTest.create(command);

        // then
        verify(fileStoragePort).store(any(), eq(command.fileContent()));
        verify(fileRepository).save(any());

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getStorageKey()).isNotNull();
    }
}
