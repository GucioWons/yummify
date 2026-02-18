package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.model.FileUrl;
import com.guciowons.yummify.file.application.port.out.FileUrlProviderPort;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static com.guciowons.yummify.file.application.fixture.FileApplicationFixture.givenGetFileUrlCommand;
import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetFileUrlUsecaseTest {
    private final FileLookupService fileLookupService = mock(FileLookupService.class);
    private final FileUrlProviderPort fileUrlProvider = mock(FileUrlProviderPort.class);

    private final GetFileUrlUsecase underTest = new GetFileUrlUsecase(fileLookupService, fileUrlProvider);

    @Test
    void shouldGetFileUrl() throws MalformedURLException {
        // given
        var command = givenGetFileUrlCommand();
        var file = givenFile(1);
        var fileUrl = FileUrl.of(URI.create("https://minio.test/file.pdf").toURL());

        when(fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(file);
        when(fileUrlProvider.getUrl(file.getStorageKey())).thenReturn(fileUrl);

        // when
        var result = underTest.getPresignedUrl(command);

        // then
        verify(fileLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(fileUrlProvider).getUrl(file.getStorageKey());

        assertThat(result).isEqualTo(fileUrl);
    }
}
