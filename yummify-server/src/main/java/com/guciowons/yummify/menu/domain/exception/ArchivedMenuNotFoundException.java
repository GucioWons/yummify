package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;
import lombok.Getter;

@Getter
public class ArchivedMenuNotFoundException extends MenuDomainException {
    private final MenuVersion.Id id;

    public ArchivedMenuNotFoundException(MenuVersion.Id id) {
        super(MenuErrorMessage.ARCHIVED_MENU_VERSION_NOT_FOUND, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
