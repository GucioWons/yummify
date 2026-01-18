package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.usecase.MenuCreateUsecase;
import com.guciowons.yummify.menu.application.usecase.MenuUpdateUsecase;
import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MenuFacade {
    private final MenuCreateUsecase menuCreateUsecase;
    private final MenuUpdateUsecase menuUpdateUsecase;
    private final DomainExceptionHandler menuDomainExceptionHandler;

    public Menu create(MenuData menuData) {
        return menuDomainExceptionHandler.handle(() -> menuCreateUsecase.create(menuData));
    }

    public Menu update(UUID id, MenuData menuData) {
        return menuDomainExceptionHandler.handle(() -> menuUpdateUsecase.update(id, menuData));
    }
}
