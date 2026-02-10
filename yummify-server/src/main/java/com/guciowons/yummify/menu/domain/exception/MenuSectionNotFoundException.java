package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import lombok.Getter;

@Getter
public class MenuSectionNotFoundException extends DomainException {
    private final MenuSection.Id id;

    public MenuSectionNotFoundException(MenuSection.Id id) {
        super("Menu section not found by id");
        this.id = id;
    }
}
