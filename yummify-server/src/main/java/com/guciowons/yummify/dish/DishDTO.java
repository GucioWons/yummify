package com.guciowons.yummify.dish;

import java.util.List;
import java.util.UUID;

public record DishDTO(UUID id, String name, List<IngredientDTO> ingredients) {
}
