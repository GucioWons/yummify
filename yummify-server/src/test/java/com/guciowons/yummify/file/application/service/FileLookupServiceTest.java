package com.guciowons.yummify.file.application.service;

import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFile;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFileId;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FileLookupServiceTest {
    private final FileRepository fileRepository = mock(FileRepository.class);

    private final FileLookupService underTest = new FileLookupService(fileRepository);

    @Test
    void shouldGetFile_WhenExists() {
        // given
        var file = givenFile(1);
        var fileId = file.getId();
        var restaurantId = file.getRestaurantId();

        when(fileRepository.findByIdAndRestaurantId(fileId, restaurantId)).thenReturn(Optional.of(file));

        // when
        var result = underTest.getByIdAndRestaurantId(fileId, restaurantId);

        // then
        verify(fileRepository).findByIdAndRestaurantId(fileId, restaurantId);

        assertThat(result).isEqualTo(file);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var fileId = givenFileId(1);
        var restaurantId = givenRestaurantId(1);

        when(fileRepository.findByIdAndRestaurantId(fileId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(fileId, restaurantId))
                .isInstanceOf(FileNotFoundException.class);

        // then
        verify(fileRepository).findByIdAndRestaurantId(fileId, restaurantId);
    }
}