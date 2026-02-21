package com.guciowons.yummify.restaurant.application.port;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

import java.util.UUID;

public interface RestaurantFacadePort {
    Restaurant create(String name, TranslatedString description, Language language, RestaurantOwner owner);

    Restaurant getById(UUID id);

    Restaurant update(UUID id, String name, TranslatedString description, Language language);
}
