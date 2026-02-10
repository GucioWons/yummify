package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void shouldAddNewEntries_WhenSnapshotsHaveNoId() {
        // given
        var section = givenMenuSection(1);
        var newEntrySnapshots = List.of(givenNewMenuEntrySnapshot(1), givenNewMenuEntrySnapshot(2));

        // when
        section.updateEntries(newEntrySnapshots);

        // then
        assertThat(section.getEntries()).hasSize(2);
    }

    @Test
    void shouldUpdateExistingEntry_WhenSnapshotHasId() {
        // given
        var section = givenMenuSection(1);
        var existingMenuEntry = givenMenuEntry(1);
        existingMenuEntry.update(givenMenuEntryPrice(2));
        section.getEntries().add(existingMenuEntry);
        var newEntrySnapshots = List.of(givenExistingMenuEntrySnapshot(1));

        // when
        section.updateEntries(newEntrySnapshots);

        // then
        assertThat(section.getEntries()).hasSize(1);
        assertThat(section.getEntries().getFirst().getPrice()).isEqualTo(newEntrySnapshots.getFirst().price());
    }

    @Test
    void shouldThrowException_WhenUpdatingNonExistingEntry() {
        // given
        var section = givenMenuSection(1);
        var newEntrySnapshots = List.of(givenExistingMenuEntrySnapshot(1));

        // when + then
        assertThatThrownBy(() -> section.updateEntries(newEntrySnapshots))
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
        original.updateEntries(List.of(givenNewMenuEntrySnapshot(1)));

        // when
        var result = original.copy();

        // then
        assertThat(result.getId()).isNotEqualTo(original.getId());
        assertThat(result.getName()).isEqualTo(original.getName());
        assertThat(result.getPosition()).isEqualTo(original.getPosition());
    }
}
