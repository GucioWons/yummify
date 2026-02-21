package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.menu.application.model.mapper.MenuVersionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.*;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MenuVersionFacadeTest {
    private final CreateMenuVersionUsecase createMenuVersionUsecase = mock(CreateMenuVersionUsecase.class);
    private final GetAllArchivedMenuVersionsUsecase getAllArchivedMenuVersionsUsecase = mock(GetAllArchivedMenuVersionsUsecase.class);
    private final GetDraftMenuVersionUsecase getDraftMenuVersionUsecase = mock(GetDraftMenuVersionUsecase.class);
    private final GetPublishedMenuVersionUsecase getPublishedMenuVersionUsecase = mock(GetPublishedMenuVersionUsecase.class);
    private final GetArchivedMenuVersionUsecase getArchivedMenuVersionUsecase = mock(GetArchivedMenuVersionUsecase.class);
    private final PublishMenuVersionUsecase publishMenuVersionUsecase = mock(PublishMenuVersionUsecase.class);
    private final RestoreMenuVersionUsecase restoreMenuVersionUsecase = mock(RestoreMenuVersionUsecase.class);
    private final MenuVersionCommandMapper menuVersionCommandMapper = mock(MenuVersionCommandMapper.class);

    private final MenuVersionFacade underTest = new MenuVersionFacade(
            createMenuVersionUsecase,
            getAllArchivedMenuVersionsUsecase,
            getDraftMenuVersionUsecase,
            getPublishedMenuVersionUsecase,
            getArchivedMenuVersionUsecase,
            publishMenuVersionUsecase,
            restoreMenuVersionUsecase,
            menuVersionCommandMapper
    );

    @Test
    void shouldCreateMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var command = givenCreateMenuVersionCommand();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toCreateMenuVersionCommand(restaurantId)).thenReturn(command);
        when(createMenuVersionUsecase.create(command)).thenReturn(menuVersion);

        // when
        var result = underTest.create(restaurantId);

        // then
        verify(menuVersionCommandMapper).toCreateMenuVersionCommand(restaurantId);
        verify(createMenuVersionUsecase).create(command);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldGetAllArchivedMenuVersions() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetMenuVersionQuery();
        var menuVersions = List.of(givenMenuVersion(1), givenMenuVersion(2), givenMenuVersion(3));

        when(menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId)).thenReturn(query);
        when(getAllArchivedMenuVersionsUsecase.getAll(query)).thenReturn(menuVersions);

        // when
        var result = underTest.getAllArchived(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
        verify(getAllArchivedMenuVersionsUsecase).getAll(query);

        assertThat(result).isEqualTo(menuVersions);
    }

    @Test
    void shouldGetDraftMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetMenuVersionQuery();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toGetMenuVersionQuery(restaurantId)).thenReturn(query);
        when(getDraftMenuVersionUsecase.get(query)).thenReturn(menuVersion);

        // when
        var result = underTest.getDraft(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
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
        when(getPublishedMenuVersionUsecase.get(query)).thenReturn(menuVersion);

        // when
        var result = underTest.getPublished(restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetMenuVersionQuery(restaurantId);
        verify(getPublishedMenuVersionUsecase).get(query);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldGetArchivedMenuVersion() {
        // given
        var id = givenMenuVersionId(1).value();
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenGetArchivedMenuVersionQuery();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toGetArchivedMenuVersionQuery(id, restaurantId)).thenReturn(query);
        when(getArchivedMenuVersionUsecase.get(query)).thenReturn(menuVersion);

        // when
        var result = underTest.getArchived(id, restaurantId);

        // then
        verify(menuVersionCommandMapper).toGetArchivedMenuVersionQuery(id, restaurantId);
        verify(getArchivedMenuVersionUsecase).get(query);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldPublishMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenPublishMenuVersionCommand();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toPublishMenuVersionCommand(restaurantId)).thenReturn(query);
        when(publishMenuVersionUsecase.publish(query)).thenReturn(menuVersion);

        // when
        var result = underTest.publish(restaurantId);

        // then
        verify(menuVersionCommandMapper).toPublishMenuVersionCommand(restaurantId);
        verify(publishMenuVersionUsecase).publish(query);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldRestoreMenuVersion() {
        // given
        var id = givenMenuVersionId(1).value();
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var query = givenRestoreMenuVersionCommand();
        var menuVersion = givenMenuVersion(1);

        when(menuVersionCommandMapper.toRestoreMenuVersionCommand(id, restaurantId)).thenReturn(query);
        when(restoreMenuVersionUsecase.restore(query)).thenReturn(menuVersion);

        // when
        var result = underTest.restore(id, restaurantId);

        // then
        verify(menuVersionCommandMapper).toRestoreMenuVersionCommand(id, restaurantId);
        verify(restoreMenuVersionUsecase).restore(query);

        assertThat(result).isEqualTo(menuVersion);
    }
}
