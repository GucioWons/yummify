package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
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

    public MenuSection copy() {
        MenuSection copy = new MenuSection(Id.random(), name, position);

        entries.forEach(entry -> copy.getEntries().add(entry.copy()));

        return copy;
    }

    public void addEntry(MenuEntry entry) {
        entries.add(entry);
    }

    public MenuEntry updateEntry(MenuEntry.Id id, MenuEntry.DishId dishId, MenuEntry.Price price) {
        MenuEntry existingEntry = Optional.ofNullable(getEntriesMap().get(id))
                .orElseThrow(() -> new MenuEntryNotFoundException(id));

        existingEntry.update(dishId, price);
        return existingEntry;
    }

    public void updateName(TranslatedString name) {
        this.name = name;
    }

    public void updatePosition(Integer position) {
        this.position = position;
    }

    public void incrementPosition() {
        position++;
    }

    public void decrementPosition() {
        position--;
    }

    private Map<MenuEntry.Id, MenuEntry> getEntriesMap() {
        return entries.stream().collect(Collectors.toMap(MenuEntry::getId, Function.identity()));
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
