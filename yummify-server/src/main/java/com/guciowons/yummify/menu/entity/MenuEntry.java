package com.guciowons.yummify.menu.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MenuEntry implements BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private MenuSection section;

    private UUID dishId;

    private Integer position;

    private BigDecimal price;
}
