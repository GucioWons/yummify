package com.guciowons.yummify.menu.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_entry", schema = "menu")
public class JpaMenuEntry {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private JpaMenuSection section;
}
