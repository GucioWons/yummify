package com.guciowons.yummify.file.application;

import com.guciowons.yummify.file.PublicFileFacade;
import com.guciowons.yummify.file.application.usecase.FileCreateUsecase;
import com.guciowons.yummify.file.application.usecase.FileDeleteUsecase;
import com.guciowons.yummify.file.application.usecase.FileGetUrlUsecase;
import com.guciowons.yummify.file.application.usecase.FileUpdateUsecase;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileFacade implements PublicFileFacade {
    private final FileCreateUsecase fileCreateUsecase;
    private final FileUpdateUsecase fileUpdateUsecase;
    private final FileDeleteUsecase fileDeleteUsecase;
    private final FileGetUrlUsecase fileGetUrlUsecase;

    @Override
    public UUID create(String directory, MultipartFile file) {
        File fileEntity = fileCreateUsecase.create(directory, getFileContent(file));
        return fileEntity.getId();
    }

    @Override
    public void update(UUID id, String directory, MultipartFile file) {
        fileUpdateUsecase.update(id, directory, getFileContent(file));
    }

    @Override
    public void delete(UUID id) {
        fileDeleteUsecase.delete(id);
    }

    @Override
    public String getPresignedUrl(UUID id) {
        return fileGetUrlUsecase.getPresignedUrl(id);
    }

    private FileContent getFileContent(MultipartFile file) {
        try {
            return new FileContent(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getInputStream(),
                    file.getSize()
            );
        } catch (IOException e) {
            throw new CannotGetFileException();
        }
    }
}
