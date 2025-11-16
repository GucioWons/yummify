package com.guciowons.yummify.menu.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private List<MenuSection> sections;

    @Column(nullable = false)
    private boolean active;
}
