package com.guciowons.yummify.restaurant.application.model;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

public record UpdateRestaurantCommand(
        Restaurant.Id id,
        Restaurant.Name name,
        TranslatedString description,
        Language defaultLanguage
) {
}
