package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.menu.domain.entity.update.MenuEntryData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "menu_entry", schema = "menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuEntry {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private MenuSection section;

    @Column(nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private BigDecimal price;

    private MenuEntry(UUID id, MenuSection section) {
        this.id = id;
        this.section = section;
    }

    public static MenuEntry from(MenuEntryData data, MenuSection section) {
        MenuEntry entry = new MenuEntry(UUID.randomUUID(), section);
        entry.syncWith(data);
        return entry;
    }

    public void syncWith(MenuEntryData updateData) {
        this.dishId = updateData.dishId();
        this.position = updateData.position();
        this.price = updateData.price();
    }
}
