package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.BaseEntity;
import com.guciowons.yummify.common.core.domain.entity.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "menu", schema = "menu")
public class Menu implements BaseEntity, RestaurantScoped {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuSection> sections = new ArrayList<>();

    @Column(nullable = false)
    private boolean active;

    public void addSection(MenuSection section) {
        sections.add(section);
        section.setMenu(this);
    }

    public void rebuildSections(List<MenuSection> newSections) {
        sections.clear();
        newSections.forEach(this::addSection);
    }
}
