package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MenuSectionNotFoundException extends DomainException {
    private final UUID id;

    public MenuSectionNotFoundException(UUID id) {
        super("Menu section not found");
        this.id = id;
    }
}
