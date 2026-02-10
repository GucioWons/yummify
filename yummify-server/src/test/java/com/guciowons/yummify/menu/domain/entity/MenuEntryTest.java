package com.guciowons.yummify.menu.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class MenuEntryTest {
    @Test
    void shouldCreateMenuEntry() {
        // given
        var dishId = givenMenuEntryDishId(1);
        var price = givenMenuEntryPrice(1);

        // when
        var result = MenuEntry.create(dishId, price);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getDishId()).isEqualTo(dishId);
        assertThat(result.getPrice()).isEqualTo(price);
    }

    @Test
    void shouldUpdateMenuEntry() {
        // given
        var menuEntry = givenMenuEntry(1);
        var newPrice = givenMenuEntryPrice(2);

        // when
        menuEntry.update(newPrice);

        // then
        assertThat(menuEntry.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void shouldCopySection() {
        // given
        var original = givenMenuEntry(1);

        // when
        var result = original.copy();

        // then
        assertThat(result.getId()).isNotEqualTo(original.getId());
        assertThat(result.getDishId()).isEqualTo(original.getDishId());
        assertThat(result.getPrice()).isEqualTo(original.getPrice());
    }
}
