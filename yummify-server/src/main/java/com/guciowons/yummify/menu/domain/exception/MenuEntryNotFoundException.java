package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import lombok.Getter;

@Getter
public class MenuEntryNotFoundException extends DomainException {
    private final MenuEntry.Id id;

    public MenuEntryNotFoundException(MenuEntry.Id id) {
        super("Menu entry not found by id");
        this.id = id;
    }
}
