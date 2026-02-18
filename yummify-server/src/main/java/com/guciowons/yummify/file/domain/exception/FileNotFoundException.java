package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.file.domain.entity.File;
import lombok.Getter;

@Getter
public class FileNotFoundException extends DomainException {
    private final File.Id id;

    public FileNotFoundException(File.Id id) {
        super("File not found");
        this.id = id;
    }
}
