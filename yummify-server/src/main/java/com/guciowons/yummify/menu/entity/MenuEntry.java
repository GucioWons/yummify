package com.guciowons.yummify.menu.entity;

import com.guciowons.yummify.common.core.entity.Positioned;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "menu_entry", schema = "menu")
public class MenuEntry implements Positioned {
    @Id
    @GeneratedValue
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
}
