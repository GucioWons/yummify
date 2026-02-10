package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.model.mapper.MenuVersionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.*;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.function.Supplier;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenCreateMenuVersionCommand;
import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenGetMenuVersionQuery;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersionRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MenuVersionFacadeTest {
    private final CreateMenuVersionUsecase createMenuVersionUsecase = mock(CreateMenuVersionUsecase.class);
    private final GetAllMenuVersionsUsecase getAllMenuVersionsUsecase = mock(GetAllMenuVersionsUsecase.class);
    private final GetDraftMenuVersionUsecase getDraftMenuVersionUsecase = mock(GetDraftMenuVersionUsecase.class);
    private final GetPublishedMenuVersionUsecase getPublishedMenuVersionUsecase = mock(GetPublishedMenuVersionUsecase.class);
    private final PublishMenuVersionUsecase publishMenuVersionUsecase = mock(PublishMenuVersionUsecase.class);
    private final MenuVersionCommandMapper menuVersionCommandMapper = mock(MenuVersionCommandMapper.class);
    private final DomainExceptionHandler menuDomainExceptionHandler = mock(DomainExceptionHandler.class);

    private final MenuVersionFacade underTest = new MenuVersionFacade(
            createMenuVersionUsecase,
            getAllMenuVersionsUsecase,
            getDraftMenuVersionUsecase,
            getPublishedMenuVersionUsecase,
            publishMenuVersionUsecase,
            menuVersionCommandMapper,
            menuDomainExceptionHandler
    );

    @Test
    void shouldCreateMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var command = givenCreateMenuVersionCommand();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toCreateMenuVersionCommand(restaurantId)).thenReturn(command);
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuVersion>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuVersion>>getArgument(0).get());
        when(createMenuVersionUsecase.create(command)).thenReturn(menuVersion);

        // when
        var result = underTest.create(restaurantId);

        // then
        verify(menuVersionCommandMapper).toCreateMenuVersionCommand(restaurantId);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuVersion>>any());
        verify(createMenuVersionUsecase).create(command);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldGetAllMenuVersions() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetMenuVersionQuery();
        var menuVersions = List.of(givenMenuVersion(1), givenMenuVersion(2), givenMenuVersion(3));

        when(menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId)).thenReturn(query);
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<List<MenuVersion>>>any()))
                .thenAnswer(inv -> inv.<Supplier<List<MenuVersion>>>getArgument(0).get());
        when(getAllMenuVersionsUsecase.getAll(query)).thenReturn(menuVersions);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<List<MenuVersion>>>any());
        verify(getAllMenuVersionsUsecase).getAll(query);

        assertThat(result).isEqualTo(menuVersions);
    }

    @Test
    void shouldGetDraftMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetMenuVersionQuery();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId)).thenReturn(query);
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuVersion>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuVersion>>getArgument(0).get());
        when(getDraftMenuVersionUsecase.get(query)).thenReturn(menuVersion);

        // when
        var result = underTest.getDraft(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuVersion>>any());
        verify(getDraftMenuVersionUsecase).get(query);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldGetPublishedMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetMenuVersionQuery();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId)).thenReturn(query);
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuVersion>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuVersion>>getArgument(0).get());
        when(getPublishedMenuVersionUsecase.get(query)).thenReturn(menuVersion);

        // when
        var result = underTest.getPublished(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuVersion>>any());
        verify(getPublishedMenuVersionUsecase).get(query);

        assertThat(result).isEqualTo(menuVersion);
    }
}
