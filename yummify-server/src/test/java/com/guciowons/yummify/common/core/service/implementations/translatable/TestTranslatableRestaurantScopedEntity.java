package com.guciowons.yummify.common.core.service.implementations.translatable;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import com.guciowons.yummify.common.i8n.TranslatedString;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TestTranslatableRestaurantScopedEntity implements BaseEntity, RestaurantScoped {
    private UUID id;
    private UUID restaurantId;
    private TranslatedString test;
}
