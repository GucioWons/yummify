package com.guciowons.yummify.restaurant.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.restaurant.application.RestaurantFacade;
import com.guciowons.yummify.restaurant.application.port.RestaurantFacadePort;
import com.guciowons.yummify.restaurant.infrastructure.decorator.rest.RestaurantFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

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
    @Primary
    RestaurantFacadePort restaurantFacadePort(RestaurantFacade restaurantFacade) {
        return new RestaurantFacadeApiExceptionDecorator(restaurantFacade);
    }
}
