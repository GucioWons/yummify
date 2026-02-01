package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.file.domain.entity.value.FileId;
import lombok.Getter;

@Getter
public class FileNotFoundException extends DomainException {
    private final FileId id;

    public FileNotFoundException(FileId id) {
        super("File not found");
        this.id = id;
    }
}
