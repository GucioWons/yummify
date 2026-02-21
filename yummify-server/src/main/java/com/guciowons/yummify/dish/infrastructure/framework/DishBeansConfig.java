package com.guciowons.yummify.dish.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.DishFacade;
import com.guciowons.yummify.dish.application.port.DishFacadePort;
import com.guciowons.yummify.dish.infrastructure.decorator.rest.DishFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

@ComponentScan(
        basePackages = "com.guciowons.yummify.dish",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class DishBeansConfig {
    @Bean
    @Primary
    DishFacadePort dishFacadePort(DishFacade facade) {
        return new DishFacadeApiExceptionDecorator(facade);
    }
}
