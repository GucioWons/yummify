package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.domain.entity.update.MenuSectionData;
import com.guciowons.yummify.menu.domain.entity.value.MenuId;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.restaurant.RestaurantId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu", schema = "menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @EmbeddedId
    private MenuId id;

    @Embedded
    private RestaurantId restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MenuSection> sections = new ArrayList<>();

    private Menu(UUID id, UUID restaurantId) {
        this.id = MenuId.of(id);
        this.restaurantId = RestaurantId.of(restaurantId);
    }

    public static Menu create(MenuData data) throws MenuSectionNotFoundException, MenuEntryNotFoundException {
        Menu menu = new Menu(UUID.randomUUID(), data.restaurantId());
        menu.syncSections(data.sections());
        return menu;
    }

    public void syncSections(List<MenuSectionData> sectionsData) throws MenuSectionNotFoundException, MenuEntryNotFoundException {
        Map<UUID, MenuSection> existingById = getSectionsMap();

        sections.clear();

        for (MenuSectionData sectionData : sectionsData) {
            if (sectionData.id() == null) {
                sections.add(MenuSection.from(sectionData, this));
            } else {
                MenuSection section = existingById.get(sectionData.id());
                updateSection(section, sectionData);
                sections.add(section);
            }
        }
    }

    private Map<UUID, MenuSection> getSectionsMap() {
        return sections.stream()
                .filter(section -> section.getId() != null)
                .collect(Collectors.toMap(MenuSection::getId, Function.identity()));
    }

    private void updateSection(MenuSection section, MenuSectionData sectionData) throws MenuSectionNotFoundException, MenuEntryNotFoundException {
        if (section == null) {
            throw new MenuSectionNotFoundException(sectionData.id());
        }
        section.syncWith(sectionData);
    }
}
