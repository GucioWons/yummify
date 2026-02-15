package com.guciowons.yummify.ingredient.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Ingredient {
    private final Id id;
    private final RestaurantId restaurantId;
    private TranslatedString name;

    public static Ingredient create(RestaurantId restaurantId, TranslatedString name) {
        return new Ingredient(Id.random(), restaurantId, name);
    }

    public void updateDetails(TranslatedString name) {
        this.name = name;
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id random() {
            return new Id(UUID.randomUUID());
        }

        public static Id of(UUID value) {
            return new Id(value);
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }
}
