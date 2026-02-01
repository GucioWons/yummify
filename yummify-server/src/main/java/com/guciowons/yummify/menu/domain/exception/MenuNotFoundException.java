package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.entity.value.MenuId;
import lombok.Getter;

@Getter
public class MenuNotFoundException extends DomainException {
    private final MenuId id;

    public MenuNotFoundException(MenuId id) {
        super("Menu not found");
        this.id = id;
    }
}
