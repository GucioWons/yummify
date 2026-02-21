package com.guciowons.yummify.dish.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import com.guciowons.yummify.dish.application.exception.DishDomainExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

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
    public DomainExceptionHandler dishDomainExceptionHandler(
            DishDomainExceptionMapper dishDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(dishDomainExceptionMapper);
    }
}
