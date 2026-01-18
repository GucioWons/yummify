package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class MenuDishesNotFoundException extends DomainException {
    private final List<UUID> ids;

    public MenuDishesNotFoundException(List<UUID> ids) {
        super("Menu dishes not found");
        this.ids = ids;
    }
}
