package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.exception.CannotUpdateMenuSectionPositionException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuVersionIsNotDraftException;
import com.guciowons.yummify.menu.domain.exception.MenuVersionIsNotPublishedException;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MenuVersion {
    private final Id id;
    private final RestaurantId restaurantId;
    private final List<MenuSection> sections = new ArrayList<>();
    private final Integer version;
    private Status status;
    private Instant archivedAt;

    public static MenuVersion create(RestaurantId restaurantId) {
        return new MenuVersion(Id.random(), restaurantId, 1, Status.DRAFT, null);
    }

    public void publish() {
        ensureDraft();
        this.status = Status.PUBLISHED;
    }

    public void archive(Instant archivedAt) {
        ensurePublished();
        this.status = Status.ARCHIVED;
        this.archivedAt = archivedAt;
    }

    public MenuSection addSection(TranslatedString name) {
        ensureDraft();

        MenuSection newSection = MenuSection.create(name, sections.size() + 1);
        sections.add(newSection);

        return newSection;
    }

    public MenuSection updateSectionEntries(MenuSection.Id sectionId, List<MenuEntrySnapshot> entrySnapshots) {
        ensureDraft();

        MenuSection section = findSection(sectionId);
        section.updateEntries(entrySnapshots);
        return section;
    }

    public MenuSection updateSectionName(MenuSection.Id sectionId, TranslatedString name) {
        ensureDraft();

        MenuSection section = findSection(sectionId);
        section.updateName(name);
        return section;
    }

    public void updateSectionPosition(MenuSection.Id sectionId, Integer position) {
        ensureDraft();

        if (position < 1 || sections.size() < 2 || position > sections.size()) {
            throw new CannotUpdateMenuSectionPositionException();
        }

        MenuSection section = findSection(sectionId);
        if (position.equals(section.getPosition())) {
            throw new CannotUpdateMenuSectionPositionException();
        }else if (position > section.getPosition()) {
            shiftSectionsDown(section.getPosition(), position);
        } else {
            shiftSectionsUp(section.getPosition(), position);
        }

        section.updatePosition(position);
    }

    public MenuVersion createNextDraft() {
        ensurePublished();

        MenuVersion nextDraft = new MenuVersion(Id.random(), restaurantId, version + 1, Status.DRAFT, null);

        sections.forEach(section -> nextDraft.getSections().add(section.copy()));

        return nextDraft;
    }

    public void restoreFrom(MenuVersion source) {
        ensureDraft();

        sections.clear();
        source.getSections().forEach(section -> sections.add(section.copy()));
    }

    private MenuSection findSection(MenuSection.Id sectionId) {
        return sections.stream()
                .filter(section -> section.getId().equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new MenuSectionNotFoundException(sectionId));
    }

    private void shiftSectionsDown(Integer oldPosition, Integer newPosition) {
        sections.stream()
                .filter(s -> s.getPosition() > oldPosition && s.getPosition() <= newPosition)
                .forEach(MenuSection::decrementPosition);
    }

    private void shiftSectionsUp(Integer oldPosition, Integer newPosition) {
        sections.stream()
                .filter(s -> s.getPosition() >= newPosition && s.getPosition() < oldPosition)
                .forEach(MenuSection::incrementPosition);
    }

    private void ensurePublished() {
        if (status != Status.PUBLISHED) {
            throw new MenuVersionIsNotPublishedException();
        }
    }

    private void ensureDraft() {
        if (status != Status.DRAFT) {
            throw new MenuVersionIsNotDraftException();
        }
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id of(UUID value) {
            return new Id(value);
        }

        public static Id random() {
            return of(UUID.randomUUID());
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }

    public enum Status {
        PUBLISHED,
        DRAFT,
        ARCHIVED
    }
}
