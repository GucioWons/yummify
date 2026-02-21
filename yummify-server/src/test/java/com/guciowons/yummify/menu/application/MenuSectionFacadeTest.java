package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.model.mapper.MenuSectionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.CreateMenuSectionUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionEntriesUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionNameUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionPositionUsecase;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
    private final DomainExceptionHandler menuDomainExceptionHandler = mock(DomainExceptionHandler.class);

    private final MenuSectionFacade underTest = new MenuSectionFacade(
            createMenuSectionUsecase,
            updateMenuSectionEntriesUsecase,
            updateMenuSectionNameUsecase,
            updateMenuSectionPositionUsecase,
            menuSectionCommandMapper,
            menuDomainExceptionHandler
    );

    @Test
    void shouldCreateMenuSection() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var name = Map.of("EN", "Pizza");
        var command = givenCreateMenuSectionCommand();
        var menuSection = givenMenuSection(1);

        when(menuSectionCommandMapper.toCreateMenuSectionCommand(restaurantId, name)).thenReturn(command);
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuSection>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuSection>>getArgument(0).get());
        when(createMenuSectionUsecase.create(command)).thenReturn(menuSection);

        // when
        var result = underTest.create(restaurantId, name);

        // then
        verify(menuSectionCommandMapper).toCreateMenuSectionCommand(restaurantId, name);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuSection>>any());
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
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuSection>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuSection>>getArgument(0).get());
        when(updateMenuSectionEntriesUsecase.update(command)).thenReturn(menuSection);

        // when
        var result = underTest.updateEntries(sectionId.value(), restaurantId, entrySnapshots);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionEntriesCommand(sectionId.value(), restaurantId, entrySnapshots);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuSection>>any());
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
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<MenuSection>>any()))
                .thenAnswer(inv -> inv.<Supplier<MenuSection>>getArgument(0).get());
        when(updateMenuSectionNameUsecase.update(command)).thenReturn(menuSection);

        // when
        var result = underTest.updateName(sectionId.value(), restaurantId, name);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionNameCommand(sectionId.value(), restaurantId, name);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<MenuSection>>any());
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
        when(menuDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<List<MenuSection>>>any()))
                .thenAnswer(inv -> inv.<Supplier<List<MenuSection>>>getArgument(0).get());
        when(updateMenuSectionPositionUsecase.update(command)).thenReturn(menuSections);

        // when
        var result = underTest.updatePosition(sectionId.value(), restaurantId, position);

        // then
        verify(menuSectionCommandMapper).toUpdateMenuSectionPositionCommand(sectionId.value(), restaurantId, position);
        verify(menuDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<List<MenuSection>>>any());
        verify(updateMenuSectionPositionUsecase).update(command);

        assertThat(result).isEqualTo(menuSections);
    }
}
