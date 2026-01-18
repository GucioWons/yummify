package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.menu.domain.entity.update.MenuEntryData;
import com.guciowons.yummify.menu.domain.entity.update.MenuSectionData;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu_section", schema = "menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSection {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private Integer position;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private TranslatedString name;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MenuEntry> entries = new ArrayList<>();

    private MenuSection(UUID id, Menu menu) {
        this.id = id;
        this.menu = menu;
    }

    public static MenuSection from(MenuSectionData data, Menu menu) throws MenuEntryNotFoundException {
        MenuSection section = new MenuSection(UUID.randomUUID(), menu);
        section.syncWith(data);
        return section;
    }

    public void syncWith(MenuSectionData data) throws MenuEntryNotFoundException {
        this.position = data.position();
        this.name = data.name();

        Map<UUID, MenuEntry> existingById = getEntriesMap();

        entries.clear();

        for (MenuEntryData entryData : data.entries()) {
            if (entryData.id() == null) {
                entries.add(MenuEntry.from(entryData, this));
            } else {
                MenuEntry entry = existingById.get(entryData.id());
                updateEntry(entry, entryData);
                entries.add(entry);
            }
        }
    }

    private Map<UUID, MenuEntry> getEntriesMap() {
        return entries.stream()
                .filter(entry -> entry.getId() != null)
                .collect(Collectors.toMap(MenuEntry::getId, Function.identity()));
    }

    private void updateEntry(MenuEntry entry, MenuEntryData data) throws MenuEntryNotFoundException {
        if (entry == null) {
            throw new MenuEntryNotFoundException(data.id());
        }
        entry.syncWith(data);
    }
}
