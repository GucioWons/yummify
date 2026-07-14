package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;
import lombok.Getter;

@Getter
public class MenuEntryNotFoundException extends MenuDomainException {
    private final MenuEntry.Id id;

    public MenuEntryNotFoundException(MenuEntry.Id id) {
        super(MenuErrorMessage.MENU_ENTRY_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
