package com.guciowons.yummify.ingredient.fixture;

import com.guciowons.yummify.ingredient.application.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;

import java.util.UUID;

public class IngredientFixture {
    public static Ingredient emptyEntity() {
        return new Ingredient();
    }
    public static Ingredient withIdEntity(UUID id) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        return ingredient;
    }

    public static IngredientManageDTO emptyManageDTO() {
        return new IngredientManageDTO(null, null);
    }

    public static IngredientManageDTO withIdManageDTO(UUID id) {
        return new IngredientManageDTO(id, null);
    }

    public static IngredientClientDTO withIdClientDTO(UUID id) {
        return new IngredientClientDTO(id, null);
    }
}
