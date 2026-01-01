package com.guciowons.yummify.dish.application.dish.framework;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.dish.port.DishRepositoryPort;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DishBeansConfig {
    @Bean
    public RestaurantScopedService<Dish> dishRestaurantScopedService(
            DishRepositoryPort dishRepositoryPort
    ) {
        return new RestaurantScopedService<>(
                dishRepositoryPort,
                DishNotFoundException::new
        );
    }
}
