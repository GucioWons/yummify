package com.guciowons.yummify.menu.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.menu.application.MenuSectionFacade;
import com.guciowons.yummify.menu.application.MenuVersionFacade;
import com.guciowons.yummify.menu.application.port.MenuSectionFacadePort;
import com.guciowons.yummify.menu.application.port.MenuVersionFacadePort;
import com.guciowons.yummify.menu.infrastructure.decorator.rest.MenuSectionFacadeApiExceptionDecorator;
import com.guciowons.yummify.menu.infrastructure.decorator.rest.MenuVersionFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

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
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    @Primary
    MenuVersionFacadePort menuVersionFacadePort(MenuVersionFacade menuVersionFacade) {
        return new MenuVersionFacadeApiExceptionDecorator(menuVersionFacade);
    }

    @Bean
    @Primary
    MenuSectionFacadePort menuSectionFacadePort(MenuSectionFacade menuSectionFacade) {
        return new MenuSectionFacadeApiExceptionDecorator(menuSectionFacade);
    }
}
