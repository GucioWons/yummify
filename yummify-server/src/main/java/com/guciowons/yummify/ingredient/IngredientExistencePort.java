package com.guciowons.yummify.ingredient;

import com.guciowons.yummify.restaurant.RestaurantId;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "IngredientExistencePort")
public interface IngredientExistencePort {
    List<UUID> findMissing(List<UUID> ids, RestaurantId restaurantId);
}
