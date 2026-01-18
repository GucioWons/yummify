package com.guciowons.yummify.menu.infrastructure.framework;

import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.dish.DishExistencePort;
import com.guciowons.yummify.menu.application.MenuFacade;
import com.guciowons.yummify.menu.application.exception.MenuDomainExceptionMapper;
import com.guciowons.yummify.menu.application.service.MenuLookupService;
import com.guciowons.yummify.menu.application.usecase.MenuCreateUsecase;
import com.guciowons.yummify.menu.application.usecase.MenuUpdateUsecase;
import com.guciowons.yummify.menu.domain.port.out.MenuRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuBeansConfig {
    @Bean
    public MenuFacade menuFacade(
            MenuCreateUsecase menuCreateUsecase,
            MenuUpdateUsecase menuUpdateUsecase,
            DomainExceptionHandler menuDomainExceptionHandler
    ) {
        return new MenuFacade(menuCreateUsecase, menuUpdateUsecase, menuDomainExceptionHandler);
    }

    @Bean
    public MenuCreateUsecase menuCreateUsecase(
            MenuRepositoryPort menuRepository,
            DishExistencePort dishExistencePort
    ) {
        return new MenuCreateUsecase(menuRepository, dishExistencePort);
    }

    @Bean
    public MenuUpdateUsecase menuUpdateUsecase(
            MenuLookupService menuLookupService,
            MenuRepositoryPort menuRepository,
            DishExistencePort dishExistencePort
    ) {
        return new MenuUpdateUsecase(menuLookupService, menuRepository, dishExistencePort);
    }

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
