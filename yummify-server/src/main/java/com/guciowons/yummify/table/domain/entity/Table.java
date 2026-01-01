package com.guciowons.yummify.table.domain.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "my_table", schema = "my_table")
public class Table implements BaseEntity, RestaurantScoped {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Column
    private UUID userId;

    @Column(nullable = false)
    private String name;
}
