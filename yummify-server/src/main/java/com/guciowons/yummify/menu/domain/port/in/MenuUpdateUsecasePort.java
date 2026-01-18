package com.guciowons.yummify.menu.domain.port.in;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;

import java.util.UUID;

public interface MenuUpdateUsecasePort {
    Menu update(UUID id, MenuData menuData) throws MenuNotFoundException, MenuSectionNotFoundException, MenuEntryNotFoundException;
}
