package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.menu.application.model.*;
import com.guciowons.yummify.menu.application.model.mapper.MenuVersionCommandMapper;
import com.guciowons.yummify.menu.application.port.MenuVersionFacadePort;
import com.guciowons.yummify.menu.application.usecase.*;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuVersionFacade implements MenuVersionFacadePort {
    private final CreateMenuVersionUsecase createMenuVersionUsecase;
    private final GetAllArchivedMenuVersionsUsecase getAllArchivedMenuVersionsUsecase;
    private final GetDraftMenuVersionUsecase getDraftMenuVersionUsecase;
    private final GetPublishedMenuVersionUsecase getPublishedMenuVersionUsecase;
    private final GetArchivedMenuVersionUsecase getArchivedMenuVersionUsecase;
    private final PublishMenuVersionUsecase publishMenuVersionUsecase;
    private final RestoreMenuVersionUsecase restoreMenuVersionUsecase;
    private final MenuVersionCommandMapper menuVersionCommandMapper;

    @Override
    public MenuVersion create(UUID restaurantId) {
        CreateMenuVersionCommand command = menuVersionCommandMapper.toCreateMenuVersionCommand(restaurantId);
        return createMenuVersionUsecase.create(command);
    }

    @Override
    public List<MenuVersion> getAllArchived(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return getAllArchivedMenuVersionsUsecase.getAll(query);
    }

    @Override
    public MenuVersion getDraft(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return getDraftMenuVersionUsecase.get(query);
    }

    @Override
    public MenuVersion getPublished(UUID restaurantId) {
        GetMenuVersionQuery query = menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId);
        return getPublishedMenuVersionUsecase.get(query);
    }

    @Override
    public MenuVersion getArchived(UUID id, UUID restaurantId) {
        GetArchivedMenuVersionQuery query = menuVersionCommandMapper.toGetArchivedMenuVersionQuery(id, restaurantId);
        return getArchivedMenuVersionUsecase.get(query);
    }

    @Override
    public MenuVersion publish(UUID restaurantId) {
        PublishMenuVersionCommand command = menuVersionCommandMapper.toPublishMenuVersionCommand(restaurantId);
        return publishMenuVersionUsecase.publish(command);
    }

    @Override
    public MenuVersion restore(UUID id, UUID restaurantId) {
        RestoreMenuVersionCommand command = menuVersionCommandMapper.toRestoreMenuVersionCommand(id, restaurantId);
        return restoreMenuVersionUsecase.restore(command);
    }
}
