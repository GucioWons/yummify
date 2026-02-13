package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.menu.domain.exception.CannotUpdateMenuSectionPositionException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuVersionIsNotDraftException;
import com.guciowons.yummify.menu.domain.exception.MenuVersionIsNotPublishedException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuVersionTest {
    @Test
    void shouldCreateMenuVersion() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);

        // when
        var menuVersion = MenuVersion.create(restaurantId);

        // then
        assertThat(menuVersion.getId()).isNotNull();
        assertThat(menuVersion.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(menuVersion.getSections()).isEmpty();
        assertThat(menuVersion.getVersion()).isEqualTo(1);
        assertThat(menuVersion.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
    }

    @Test
    void shouldPublishMenuVersion() {
        // given
        var menuVersion = givenMenuVersion(1);

        // when
        menuVersion.publish();

        // then
        assertThat(menuVersion.getStatus()).isEqualTo(MenuVersion.Status.PUBLISHED);
    }

    @Test
    void shouldArchiveMenuVersion() {
        // given
        var now = Instant.now();
        var menuVersion = givenMenuVersion(1);
        menuVersion.publish();

        // when
        menuVersion.archive(now);

        // then
        assertThat(menuVersion.getStatus()).isEqualTo(MenuVersion.Status.ARCHIVED);
        assertThat(menuVersion.getArchivedAt()).isEqualTo(now);
    }

    @Test
    void shouldAddSectionToMenuVersion() {
        // given
        var menuVersion = givenMenuVersion(1);
        var name = givenMenuSectionName(1);

        // when
        var section = menuVersion.addSection(name);

        // then
        assertThat(menuVersion.getSections()).contains(section);
        assertThat(menuVersion.getSections()).hasSize(1);
        assertThat(section.getPosition()).isEqualTo(1);
    }

    @Test
    void shouldNotAddSectionToMenuVersionAndThrowException_WhenMenuVersionIsNotDraft() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.publish();
        var name = givenMenuSectionName(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.addSection(name))
                .isInstanceOf(MenuVersionIsNotDraftException.class);
    }

    @Test
    void shouldUpdateSectionName() {
        // given
        var menuVersion = givenMenuVersion(1);
        var section = menuVersion.addSection(givenMenuSectionName(1));
        var newName = givenMenuSectionName(2);

        // when
        menuVersion.updateSectionName(section.getId(), newName);

        // then
        assertThat(section.getName()).isEqualTo(newName);
    }

    @Test
    void shouldNotUpdateSectionNameAndThrowException_WhenMenuVersionIsNotDraft() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.publish();
        var sectionId = givenMenuSectionId(1);
        var newName = givenMenuSectionName(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionName(sectionId, newName))
                .isInstanceOf(MenuVersionIsNotDraftException.class);
    }

    @Test
    void shouldNotUpdateSectionNameAndThrowException_WhenSectionNotFound() {
        // given
        var menuVersion = givenMenuVersion(1);
        var sectionId = givenMenuSectionId(1);
        var name = givenMenuSectionName(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionName(sectionId, name))
                .isInstanceOf(MenuSectionNotFoundException.class);
    }

    @Test
    void shouldShiftSectionsDown_WhenPositionIncreased() {
        // given
        var menuVersion = givenMenuVersion(1);
        var section1 = menuVersion.addSection(givenMenuSectionName(1));
        var section2 = menuVersion.addSection(givenMenuSectionName(2));

        // when
        menuVersion.updateSectionPosition(section1.getId(), 2);

        // then
        assertThat(section1.getPosition()).isEqualTo(2);
        assertThat(section2.getPosition()).isEqualTo(1);
    }

    @Test
    void shouldShiftSectionsUp_WhenPositionDecreased() {
        // given
        var menuVersion = givenMenuVersion(1);
        var section1 = menuVersion.addSection(givenMenuSectionName(1));
        var section2 = menuVersion.addSection(givenMenuSectionName(2));

        // when
        menuVersion.updateSectionPosition(section2.getId(), 1);

        // then
        assertThat(section2.getPosition()).isEqualTo(1);
        assertThat(section1.getPosition()).isEqualTo(2);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenMenuVersionIsNotDraft() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.addSection(givenMenuSectionName(1));
        menuVersion.addSection(givenMenuSectionName(2));
        menuVersion.publish();
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(sectionId, 1))
                .isInstanceOf(MenuVersionIsNotDraftException.class);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenPositionIsLessThanOne() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.addSection(givenMenuSectionName(1));
        menuVersion.addSection(givenMenuSectionName(2));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(sectionId, 0))
                .isInstanceOf(CannotUpdateMenuSectionPositionException.class);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenSectionsSizeIsLessThanTwo() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.addSection(givenMenuSectionName(1));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(sectionId, 1))
                .isInstanceOf(CannotUpdateMenuSectionPositionException.class);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenPositionIsGreaterThanSectionsSize() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.addSection(givenMenuSectionName(1));
        menuVersion.addSection(givenMenuSectionName(2));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(sectionId, 3))
                .isInstanceOf(CannotUpdateMenuSectionPositionException.class);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenSectionNotFound() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.addSection(givenMenuSectionName(1));
        menuVersion.addSection(givenMenuSectionName(2));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(sectionId, 1))
                .isInstanceOf(MenuSectionNotFoundException.class);
    }

    @Test
    void shouldNotUpdateSectionPositionAndThrowException_WhenNewAndOldPositionsAreEqual() {
        // given
        var menuVersion = givenMenuVersion(1);
        var section = menuVersion.addSection(givenMenuSectionName(1));
        menuVersion.addSection(givenMenuSectionName(2));

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionPosition(section.getId(), 1))
                .isInstanceOf(CannotUpdateMenuSectionPositionException.class);
    }

    @Test
    void shouldUpdateSectionEntries() {
        // given
        var menuVersion = givenMenuVersion(1);
        var section = menuVersion.addSection(givenMenuSectionName(1));
        var newEntries = List.of(givenNewMenuEntrySnapshot(1), givenNewMenuEntrySnapshot(2));

        // when
        menuVersion.updateSectionEntries(section.getId(), newEntries);

        // then
        assertThat(section.getEntries()).hasSize(2);
    }

    @Test
    void shouldNotUpdateSectionEntriesAndThrowException_WhenVersionIsNotDraft() {
        // given
        var menuVersion = givenMenuVersion(1);
        menuVersion.publish();
        var newEntries = List.of(givenNewMenuEntrySnapshot(1), givenNewMenuEntrySnapshot(2));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionEntries(sectionId, newEntries))
                .isInstanceOf(MenuVersionIsNotDraftException.class);
    }

    @Test
    void shouldNotUpdateSectionEntriesAndThrowException_WhenSectionNotFound() {
        // given
        var menuVersion = givenMenuVersion(1);
        var newEntries = List.of(givenNewMenuEntrySnapshot(1), givenNewMenuEntrySnapshot(2));
        var sectionId = givenMenuSectionId(1);

        // when + then
        assertThatThrownBy(() -> menuVersion.updateSectionEntries(sectionId, newEntries))
                .isInstanceOf(MenuSectionNotFoundException.class);
    }

    @Test
    void shouldReturnDeepCopy_WhenCreateNextDraft() {
        // given
        var original = givenMenuVersion(1);
        var section = original.addSection(givenMenuSectionName(1));
        original.updateSectionEntries(section.getId(), List.of(givenNewMenuEntrySnapshot(1)));
        original.publish();

        // when
        var result = original.createNextDraft();

        // then
        assertThat(result.getId()).isNotEqualTo(original.getId());
        assertThat(result.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
        assertThat(result.getVersion()).isEqualTo(original.getVersion() + 1);
        assertThat(result.getSections()).hasSize(original.getSections().size());
    }

    @Test
    void shouldNotCreateNextDraftAndThrowException_WhenVersionIsNotPublished() {
        // given
        var original = givenMenuVersion(1);

        // when
        assertThatThrownBy(original::createNextDraft)
                .isInstanceOf(MenuVersionIsNotPublishedException.class);
    }

    @Test
    void shouldCopySections_WhenRestoreFromArchived() {
        // given
        var draft = givenMenuVersion(1);
        var archived = givenMenuVersion(2);
        var section = archived.addSection(givenMenuSectionName(1));
        archived.updateSectionEntries(section.getId(), List.of(givenNewMenuEntrySnapshot(1)));
        archived.publish();
        archived.archive(Instant.now());

        // when
        draft.restoreFrom(archived);

        // then
        assertThat(draft.getId()).isNotEqualTo(archived.getId());
        assertThat(draft.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
        assertThat(draft.getVersion()).isNotEqualTo(archived.getVersion());
        assertThat(draft.getSections()).hasSize(archived.getSections().size());
    }

    @Test
    void shouldNotRestoreAndThrowException_WhenVersionIsNotDraft() {
        // given
        var draft = givenMenuVersion(1);
        draft.publish();
        var archived = givenMenuVersion(2);

        // when
        assertThatThrownBy(() -> draft.restoreFrom(archived))
                .isInstanceOf(MenuVersionIsNotDraftException.class);
    }
}
