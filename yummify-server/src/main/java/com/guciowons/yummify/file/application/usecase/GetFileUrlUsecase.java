package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.application.model.GetFileUrlCommand;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.model.FileUrl;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.application.port.out.FileUrlProviderPort;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetFileUrlUsecase {
    private final FileLookupService fileLookupService;
    private final FileUrlProviderPort fileUrlProvider;

    public FileUrl getPresignedUrl(GetFileUrlCommand command) throws FileNotFoundException {
        File file = fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        return fileUrlProvider.getUrl(file.getStorageKey());
    }
}
