package com.guciowons.yummify.menu.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.exception.MenuDomainExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.Clock;

@ComponentScan(
        basePackages = "com.guciowons.yummify.menu",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class MenuBeansConfig {

    @Bean
    public DomainExceptionHandler menuDomainExceptionHandler(
            MenuDomainExceptionMapper menuDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(menuDomainExceptionMapper);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
