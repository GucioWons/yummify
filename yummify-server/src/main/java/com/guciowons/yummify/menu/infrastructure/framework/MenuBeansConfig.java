package com.guciowons.yummify.menu.infrastructure.framework;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.exception.MenuDomainExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuBeansConfig {

    @Bean
    public DomainExceptionHandler menuDomainExceptionHandler(
            MenuDomainExceptionMapper menuDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(menuDomainExceptionMapper);
    }

    @Bean
    public MenuDomainExceptionMapper menuDomainExceptionMapper() {
        return new MenuDomainExceptionMapper();
    }
}
