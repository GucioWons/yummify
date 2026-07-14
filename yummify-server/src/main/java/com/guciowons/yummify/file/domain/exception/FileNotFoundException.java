package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.message.FileErrorMessage;
import lombok.Getter;

@Getter
public class FileNotFoundException extends FileDomainException {
    private final File.Id id;

    public FileNotFoundException(File.Id id) {
        super(FileErrorMessage.FILE_NOT_FOUND_EXCEPTION, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
