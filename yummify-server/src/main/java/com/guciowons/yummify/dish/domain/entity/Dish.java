package com.guciowons.yummify.dish.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Dish {
    private final Id id;
    private final RestaurantId restaurantId;
    private TranslatedString name;
    private TranslatedString description;
    private List<UUID> ingredientIds;
    private File image;

    public static Dish create(
            RestaurantId restaurantId,
            TranslatedString name,
            TranslatedString description,
            List<UUID> ingredientIds
    ) {
        return new Dish(Id.random(), restaurantId, name, description, ingredientIds, null);
    }

    public void updateDetails(TranslatedString name, TranslatedString description, List<UUID> ingredientIds) {
        this.name = name;
        this.description = description;
        this.ingredientIds = ingredientIds;
    }

    public boolean hasImage() {
        return image != null;
    }

    public void changeImage(File image) {
        this.image = image;
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
