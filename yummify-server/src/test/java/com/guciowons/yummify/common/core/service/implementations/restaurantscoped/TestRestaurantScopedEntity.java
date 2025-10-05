package com.guciowons.yummify.common.core.service.implementations.restaurantscoped;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TestRestaurantScopedEntity implements BaseEntity, RestaurantScoped {
    private UUID id;
    private UUID restaurantId;
    private String test;
}
