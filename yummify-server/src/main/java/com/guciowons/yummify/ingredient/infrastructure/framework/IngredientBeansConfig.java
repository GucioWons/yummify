package com.guciowons.yummify.ingredient.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.ingredient.application.exception.IngredientDomainExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

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
    public DomainExceptionHandler ingredientDomainExceptionHandler(
            IngredientDomainExceptionMapper ingredientDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(ingredientDomainExceptionMapper);
    }
}
