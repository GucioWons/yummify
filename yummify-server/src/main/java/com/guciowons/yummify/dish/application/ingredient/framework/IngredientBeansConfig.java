package com.guciowons.yummify.dish.application.ingredient.framework;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.exception.ingredient.IngredientNotFoundException;
import com.guciowons.yummify.dish.domain.ingredient.port.IngredientRepositoryPort;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredientBeansConfig {
    @Bean
    public RestaurantScopedService<Ingredient> ingredientRestaurantScopedService(
            IngredientRepositoryPort ingredientRepositoryPort
    ) {
        return new RestaurantScopedService<>(
                ingredientRepositoryPort,
                IngredientNotFoundException::new
        );
    }
}
