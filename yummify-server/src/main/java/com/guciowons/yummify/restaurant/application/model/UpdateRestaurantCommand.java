package com.guciowons.yummify.restaurant.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantName;

public record UpdateRestaurantCommand(
        RestaurantId id,
        RestaurantName name,
        TranslatedString description,
        Language defaultLanguage
) {
}
