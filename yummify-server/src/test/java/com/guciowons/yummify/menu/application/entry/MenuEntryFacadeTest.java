package com.guciowons.yummify.menu.application.entry;

import com.guciowons.yummify.menu.application.entry.model.mapper.MenuEntryCommandMapper;
import com.guciowons.yummify.menu.application.entry.usecase.CreateMenuEntryUsecase;
import com.guciowons.yummify.menu.application.entry.usecase.UpdateMenuEntryUsecase;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenCreateMenuEntryCommand;
import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenUpdateMenuEntryCommand;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MenuEntryFacadeTest {
    private final CreateMenuEntryUsecase createMenuEntryUsecase = mock(CreateMenuEntryUsecase.class);
    private final UpdateMenuEntryUsecase updateMenuEntryUsecase = mock(UpdateMenuEntryUsecase.class);
    private final MenuEntryCommandMapper menuEntryCommandMapper = mock(MenuEntryCommandMapper.class);

    private final MenuEntryFacade underTest = new MenuEntryFacade(
            createMenuEntryUsecase,
            updateMenuEntryUsecase,
            menuEntryCommandMapper
    );

    @Test
    void shouldCreateMenuEntry() {
        // given
        var sectionId = givenMenuSectionId(1).value();
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var dishId = givenMenuEntryDishId(1).value();
        var price = givenMenuEntryPrice(1).value();
        var command = givenCreateMenuEntryCommand(givenMenuSectionId(1));
        var expectedResult = givenMenuEntry(1);

        when(menuEntryCommandMapper.toCreateMenuEntryCommand(sectionId, restaurantId, dishId, price))
                .thenReturn(command);
        when(createMenuEntryUsecase.create(command)).thenReturn(expectedResult);

        // when
        var result = underTest.createEntry(sectionId, restaurantId, dishId, price);

        // then
        verify(menuEntryCommandMapper).toCreateMenuEntryCommand(sectionId, restaurantId, dishId, price);
        verify(createMenuEntryUsecase).create(command);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldUpdateMenuEntry() {
        // given
        var sectionId = givenMenuSectionId(1).value();
        var entryId = givenMenuEntryId(1).value();
        var restaurantId = givenMenuVersionRestaurantId(1).value();
        var dishId = givenMenuEntryDishId(1).value();
        var price = givenMenuEntryPrice(1).value();
        var command = givenUpdateMenuEntryCommand(givenMenuSectionId(1));
        var expectedResult = givenMenuEntry(1);

        when(menuEntryCommandMapper.toUpdateMenuEntryCommand(sectionId, entryId, restaurantId, dishId, price))
                .thenReturn(command);
        when(updateMenuEntryUsecase.update(command)).thenReturn(expectedResult);

        // when
        var result = underTest.updateEntry(sectionId, entryId, restaurantId, dishId, price);

        // then
        verify(menuEntryCommandMapper).toUpdateMenuEntryCommand(sectionId, entryId, restaurantId, dishId, price);
        verify(updateMenuEntryUsecase).update(command);

        assertThat(result).isEqualTo(expectedResult);
    }
}
