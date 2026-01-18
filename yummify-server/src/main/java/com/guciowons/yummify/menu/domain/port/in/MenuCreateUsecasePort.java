package com.guciowons.yummify.menu.domain.port.in;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;

public interface MenuCreateUsecasePort {
    Menu create(MenuData menuData) throws MenuSectionNotFoundException, MenuEntryNotFoundException;
}
