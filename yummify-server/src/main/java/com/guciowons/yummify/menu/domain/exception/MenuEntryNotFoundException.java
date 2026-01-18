package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MenuEntryNotFoundException extends DomainException {
    private final UUID id;

    public MenuEntryNotFoundException(UUID id) {
        super("Menu entry not found");
        this.id = id;
    }
}
