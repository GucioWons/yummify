package com.guciowons.yummify.ingredient.exposed;

import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "IngredientExistencePort")
public interface IngredientExistencePort {
    void assertAllExist(List<UUID> ids, UUID restaurantId);
}
