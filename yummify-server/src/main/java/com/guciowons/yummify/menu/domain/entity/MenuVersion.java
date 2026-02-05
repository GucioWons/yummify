package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MenuVersion {
    private final Id id;
    private final RestaurantId restaurantId;
    private final List<MenuSection> sections = new ArrayList<>();
    private Integer version;
    private Status status;

    public static MenuVersion create(RestaurantId restaurantId) {
        return new MenuVersion(Id.random(), restaurantId, 1, Status.DRAFT);
    }

    public MenuSection.Id addSection(TranslatedString name, Integer position) {
        ensureDraft();

        MenuSection newSection = MenuSection.create(name, position);
        sections.add(newSection);

        return newSection.getId();
    }

    public void updateSection(
            MenuSection.Id sectionId,
            TranslatedString name,
            Integer position,
            List<MenuEntrySnapshot> entrySnapshots
    ) {
        ensureDraft();

        MenuSection section = findSection(sectionId);
        section.update(name, position, entrySnapshots);
    }

    private MenuSection findSection(MenuSection.Id sectionId) {
        return sections.stream()
                .filter(section -> section.getId().equals(sectionId))
                .findFirst()
                .orElseThrow(() -> new MenuSectionNotFoundException(sectionId));
    }

    private void ensureDraft() {
        if (status != Status.DRAFT) {
            throw new RuntimeException();
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

    public enum Status {
        PUBLISHED,
        DRAFT,
        ARCHIVED
    }
}
