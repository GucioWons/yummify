package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.Positioned;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "menu_section", schema = "menu")
public class MenuSection implements Positioned {
    @Id
    @GeneratedValue
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
    private List<MenuEntry> entries = new ArrayList<>();

    public void addEntry(MenuEntry entry) {
        entries.add(entry);
        entry.setSection(this);
    }

    public void rebuildEntries(List<MenuEntry> entriesToAdd) {
        entries.clear();
        entriesToAdd.forEach(this::addEntry);
    }
}
