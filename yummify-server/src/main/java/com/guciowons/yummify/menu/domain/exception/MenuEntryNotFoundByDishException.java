package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;
import lombok.Getter;

@Getter
public class MenuEntryNotFoundByDishException extends MenuDomainException {
    private final MenuEntry.DishId dishId;

    public MenuEntryNotFoundByDishException(MenuEntry.DishId dishId) {
        super(MenuErrorMessage.MENU_ENTRY_NOT_FOUND_BY_ID, ErrorProperty.of("id", dishId.value()));
        this.dishId = dishId;
    }
}
