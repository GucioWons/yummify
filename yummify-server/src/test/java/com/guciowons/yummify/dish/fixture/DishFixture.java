package com.guciowons.yummify.dish.fixture;

import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.domain.entity.Dish;

import java.util.UUID;

public class DishFixture {
    public static Dish emptyEntity() {
        return new Dish();
    }
    public static Dish withIdEntity(UUID id) {
        Dish ingredient = new Dish();
        ingredient.setId(id);
        return ingredient;
    }

    public static DishManageDTO emptyManageDTO() {
        return new DishManageDTO(null, null, null, null, null);
    }

    public static DishManageDTO withIdManageDTO(UUID id) {
        return new DishManageDTO(id, null, null, null, null);
    }

    public static DishClientDTO withIdClientDTO(UUID id) {
        return new DishClientDTO(id, null, null, null, null);
    }
}
