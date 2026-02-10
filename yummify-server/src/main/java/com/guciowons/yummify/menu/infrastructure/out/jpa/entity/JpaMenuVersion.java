package com.guciowons.yummify.menu.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "menu_version", schema = "menu")
public class JpaMenuVersion {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<JpaMenuSection> sections = new ArrayList<>();

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private String status;
}
