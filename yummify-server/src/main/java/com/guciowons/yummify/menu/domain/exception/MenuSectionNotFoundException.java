package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;
import lombok.Getter;

@Getter
public class MenuSectionNotFoundException extends MenuDomainException {
    private final MenuSection.Id id;

    public MenuSectionNotFoundException(MenuSection.Id id) {
        super(MenuErrorMessage.MENU_SECTION_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
