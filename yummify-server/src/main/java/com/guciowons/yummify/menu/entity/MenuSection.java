package com.guciowons.yummify.menu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MenuSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(targetClass = UUID.class)
    @CollectionTable(name = "menu_section_dish_ids", joinColumns = @JoinColumn(name = "menu_section_id"))
    @Column(name = "dish_id", nullable = false)
    private List<UUID> dishIds;
}
