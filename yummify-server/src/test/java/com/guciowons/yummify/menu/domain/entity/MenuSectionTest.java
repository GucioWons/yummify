package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuSectionTest {
    @Test
    void shouldCreateMenuSection() {
        // given
        var name = givenMenuSectionName(1);
        var position = 1;

        // when
        var result = MenuSection.create(name, position);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPosition()).isEqualTo(position);
        assertThat(result.getEntries()).isEmpty();
    }

    @Test
    void shouldAddMenuEntry() {
        // given
        var section = givenMenuSection(1);
        var entry = givenMenuEntry(1);

        // when
        section.addEntry(entry);

        // then
        assertThat(section.getEntries()).containsExactly(entry);
    }

    @Test
    void shouldUpdateMenuEntry() {
        // given
        var section = givenMenuSection(1);
        var existingEntry = givenMenuEntry(1);
        section.addEntry(existingEntry);

        var newDishId = givenMenuEntryDishId(2);
        var newPrice = givenMenuEntryPrice(2);

        // when
        var result = section.updateEntry(existingEntry.getId(), newDishId, newPrice);

        // then
        assertThat(result.getId()).isEqualTo(existingEntry.getId());
        assertThat(result.getDishId()).isEqualTo(newDishId);
        assertThat(result.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void shouldNotUpdateMenuEntryAndThrowException_WhenMenuEntryDoesNotExist() {
        // given
        var section = givenMenuSection(1);
        var entryId = givenMenuEntryId(1);
        var newDishId = givenMenuEntryDishId(2);
        var newPrice = givenMenuEntryPrice(2);

        // when
        assertThatThrownBy(() -> section.updateEntry(entryId, newDishId, newPrice))
                .isInstanceOf(MenuEntryNotFoundException.class);
    }

    @Test
    void shouldUpdateName() {
        // given
        var section = givenMenuSection(1);
        var newName = givenMenuSectionName(2);

        // when
        section.updateName(newName);

        // then
        assertThat(section.getName()).isEqualTo(newName);
    }

    @Test
    void shouldUpdatePosition() {
        // given
        var section = givenMenuSection(1);
        var newPosition = 2;

        // when
        section.updatePosition(newPosition);

        // then
        assertThat(section.getPosition()).isEqualTo(newPosition);
    }

    @Test
    void shouldIncrementPosition() {
        // given
        var section = givenMenuSection(1);

        // when
        section.incrementPosition();

        // then
        assertThat(section.getPosition()).isEqualTo(2);
    }

    @Test
    void shouldDecrementPosition() {
        // given
        var section = givenMenuSection(2);

        // when
        section.decrementPosition();

        // then
        assertThat(section.getPosition()).isEqualTo(1);
    }

    @Test
    void shouldCopySection() {
        // given
        var original = givenMenuSection(1);
//        original.updateEntries(List.of(givenNewMenuEntrySnapshot(1)));

        // when
        var result = original.copy();

        // then
        assertThat(result.getId()).isNotEqualTo(original.getId());
        assertThat(result.getName()).isEqualTo(original.getName());
        assertThat(result.getPosition()).isEqualTo(original.getPosition());
    }
}
