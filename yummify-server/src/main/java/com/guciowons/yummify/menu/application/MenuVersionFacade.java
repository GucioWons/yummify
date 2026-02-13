package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.model.CreateMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.GetMenuVersionQuery;
import com.guciowons.yummify.menu.application.model.PublishMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.RestoreMenuVersionCommand;
import com.guciowons.yummify.menu.application.model.mapper.MenuVersionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.*;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuVersionFacade {
    private final CreateMenuVersionUsecase createMenuVersionUsecase;
    private final GetAllArchivedMenuVersionsUsecase getAllArchivedMenuVersionsUsecase;
    private final GetDraftMenuVersionUsecase getDraftMenuVersionUsecase;
    private final GetPublishedMenuVersionUsecase getPublishedMenuVersionUsecase;
    private final PublishMenuVersionUsecase publishMenuVersionUsecase;
    private final RestoreMenuVersionUsecase restoreMenuVersionUsecase;
    private final MenuVersionCommandMapper menuVersionCommandMapper;
    private final DomainExceptionHandler menuDomainExceptionHandler;

    public MenuVersion create(UUID restaurantId) {
        CreateMenuVersionCommand command = menuVersionCommandMapper.toCreateMenuVersionCommand(restaurantId);
        return menuDomainExceptionHandler.handle(() -> createMenuVersionUsecase.create(command));
    }

    public List<MenuVersion> getAllArchived(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return menuDomainExceptionHandler.handle(() -> getAllArchivedMenuVersionsUsecase.getAll(query));
    }

    public MenuVersion getDraft(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return menuDomainExceptionHandler.handle(() -> getDraftMenuVersionUsecase.get(query));
    }

    public MenuVersion getPublished(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return menuDomainExceptionHandler.handle(() -> getPublishedMenuVersionUsecase.get(query));
    }

    public MenuVersion publish(UUID restaurantId) {
        PublishMenuVersionCommand command = menuVersionCommandMapper.toPublishMenuVersionCommand(restaurantId);
        return menuDomainExceptionHandler.handle(() -> publishMenuVersionUsecase.publish(command));
    }

    public MenuVersion restore(UUID id, UUID restaurantId) {
        RestoreMenuVersionCommand command = menuVersionCommandMapper.toRestoreMenuVersionCommand(id, restaurantId);
        return menuDomainExceptionHandler.handle(() -> restoreMenuVersionUsecase.restore(command));
    }
}
