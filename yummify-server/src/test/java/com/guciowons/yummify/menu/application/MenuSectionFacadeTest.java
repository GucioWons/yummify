package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.menu.application.model.mapper.MenuSectionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.CreateMenuSectionUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionEntriesUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionNameUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionPositionUsecase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.*;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MenuSectionFacadeTest {
    private final CreateMenuSectionUsecase createMenuSectionUsecase = mock(CreateMenuSectionUsecase.class);
    private final UpdateMenuSectionEntriesUsecase updateMenuSectionEntriesUsecase = mock(UpdateMenuSectionEntriesUsecase.class);
    private final UpdateMenuSectionNameUsecase updateMenuSectionNameUsecase = mock(UpdateMenuSectionNameUsecase.class);
    private final UpdateMenuSectionPositionUsecase updateMenuSectionPositionUsecase = mock(UpdateMenuSectionPositionUsecase.class);
    private final MenuSectionCommandMapper menuSectionCommandMapper = mock(MenuSectionCommandMapper.class);

    private final MenuSectionFacade underTest = new MenuSectionFacade(
            createMenuSectionUsecase,
            updateMenuSectionEntriesUsecase,
            updateMenuSectionNameUsecase,
            updateMenuSectionPositionUsecase,
            menuSectionCommandMapper
    );

    @Test
    void shouldCreateMenuSection() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var name = Map.of("EN", "Pizza");
        var command = givenCreateMenuSectionCommand();
        var menuSection = givenMenuSection(1);

        when(menuSectionCommandMapper.toCreateMenuSectionCommand(restaurantId, name)).thenReturn(command);
        when(createMenuSectionUsecase.create(command)).thenReturn(menuSection);

        // when
        var result = underTest.create(restaurantId, name);

        // then
        verify(menuSectionCommandMapper).toCreateMenuSectionCommand(restaurantId, name);
        verify(createMenuSectionUsecase).create(command);

        assertThat(result).isEqualTo(menuSection);
    }

    @Test
    void shouldUpdateMenuSectionEntries() {
        // given
        var sectionId = givenMenuSectionId(1);
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var entrySnapshots = List.of(givenNewMenuEntrySnapshot(1), givenNewMenuEntrySnapshot(2));
        var command = givenUpdateMenuSectionEntriesCommand(sectionId);
        var menuSection = givenMenuSection(1);

        when(menuSectionCommandMapper.toUpdateMenuSectionEntriesCommand(sectionId.value(), restaurantId, entrySnapshots))
                .thenReturn(command);
        when(updateMenuSectionEntriesUsecase.update(command)).thenReturn(menuSection);

        // when
        var result = underTest.updateEntries(sectionId.value(), restaurantId, entrySnapshots);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionEntriesCommand(sectionId.value(), restaurantId, entrySnapshots);
        verify(updateMenuSectionEntriesUsecase).update(command);

        assertThat(result).isEqualTo(menuSection);
    }

    @Test
    void shouldUpdateMenuSectionName() {
        // given
        var sectionId = givenMenuSectionId(1);
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var name = Map.of("EN", "Pizza");
        var command = givenUpdateMenuSectionNameCommand(sectionId);
        var menuSection = givenMenuSection(1);

        when(menuSectionCommandMapper.toUpdateMenuSectionNameCommand(sectionId.value(), restaurantId, name))
                .thenReturn(command);
        when(updateMenuSectionNameUsecase.update(command)).thenReturn(menuSection);

        // when
        var result = underTest.updateName(sectionId.value(), restaurantId, name);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionNameCommand(sectionId.value(), restaurantId, name);
        verify(updateMenuSectionNameUsecase).update(command);

        assertThat(result).isEqualTo(menuSection);
    }

    @Test
    void shouldUpdateSectionPosition() {
        // given
        var sectionId = givenMenuSectionId(1);
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var position = 1;
        var command = givenUpdateMenuSectionPositionCommand(sectionId);
        var menuSections = List.of(givenMenuSection(1), givenMenuSection(2), givenMenuSection(3));

        when(menuSectionCommandMapper.toUpdateMenuSectionPositionCommand(sectionId.value(), restaurantId, position))
                .thenReturn(command);
        when(updateMenuSectionPositionUsecase.update(command)).thenReturn(menuSections);

        // when
        var result = underTest.updatePosition(sectionId.value(), restaurantId, position);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionPositionCommand(sectionId.value(), restaurantId, position);
        verify(updateMenuSectionPositionUsecase).update(command);

        assertThat(result).isEqualTo(menuSections);
    }
}
