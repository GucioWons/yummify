package com.guciowons.yummify.restaurant.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.restaurant.application.exception.RestaurantDomainExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        basePackages = "com.guciowons.yummify.restaurant",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class RestaurantBeansConfig {
    @Bean
    public DomainExceptionHandler restaurantDomainExceptionHandler(
            RestaurantDomainExceptionMapper restaurantDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(restaurantDomainExceptionMapper);
    }
}
