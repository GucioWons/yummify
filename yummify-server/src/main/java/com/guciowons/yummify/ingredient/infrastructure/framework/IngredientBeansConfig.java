package com.guciowons.yummify.ingredient.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.IngredientFacade;
import com.guciowons.yummify.ingredient.application.port.IngredientFacadePort;
import com.guciowons.yummify.ingredient.infrastructure.decorator.rest.IngredientFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

@ComponentScan(
        basePackages = "com.guciowons.yummify.ingredient",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class IngredientBeansConfig {
    @Bean
    @Primary
    IngredientFacadePort ingredientFacadePort(IngredientFacade ingredientFacade) {
        return new IngredientFacadeApiExceptionDecorator(ingredientFacade);
    }
}
