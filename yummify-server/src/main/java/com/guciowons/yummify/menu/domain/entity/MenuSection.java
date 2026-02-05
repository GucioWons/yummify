package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MenuSection {
    private final Id id;
    private final List<MenuEntry> entries = new ArrayList<>();
    private TranslatedString name;
    private Integer position;

    public static MenuSection create(TranslatedString name, Integer position) {
        return new MenuSection(Id.random(), name, position);
    }

    public void update(TranslatedString name, Integer position, List<MenuEntrySnapshot> entrySnapshots) {
        this.name = name;
        this.position = position;
        updateEntries(entrySnapshots);
    }

    private void updateEntries(List<MenuEntrySnapshot> entrySnapshots) {
        Map<MenuEntry.Id, MenuEntry> entriesMap = getEntriesMap();
        entries.clear();
        entrySnapshots.forEach(entrySnapshot -> createOrUpdateEntry(entrySnapshot, entriesMap));
    }

    private Map<MenuEntry.Id, MenuEntry> getEntriesMap() {
        return entries.stream().collect(Collectors.toMap(MenuEntry::getId, Function.identity()));
    }

    private void createOrUpdateEntry(MenuEntrySnapshot snapshot, Map<MenuEntry.Id, MenuEntry> entriesMap) {
        if (snapshot.id() == null) {
            entries.add(MenuEntry.create(snapshot.dishId(), snapshot.price()));
        } else {
            MenuEntry entry = Optional.ofNullable(entriesMap.get(snapshot.id()))
                    .orElseThrow(() -> new MenuEntryNotFoundException(snapshot.id()));
            entry.update(snapshot.price());
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
}
