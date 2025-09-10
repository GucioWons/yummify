package com.guciowons.yummify.table.entity;

import com.guciowons.yummify.common.temp.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Table implements RestaurantScoped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Column
    private UUID userId;

    @Column(nullable = false)
    private String name;
}
