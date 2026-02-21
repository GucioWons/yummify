package com.guciowons.yummify.auth.infrastructure.framework;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.auth.application.AuthFacade;
import com.guciowons.yummify.auth.infrastructure.decorator.rest.AuthFacadeApiExceptionDecorator;
import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        basePackages = "com.guciowons.yummify.auth",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@EnableConfigurationProperties(KeycloakProperties.class)
public class AuthBeansConfig {
    @Bean
    @Primary
    AuthFacadePort authFacadePort(AuthFacade authFacade) {
        return new AuthFacadeApiExceptionDecorator(authFacade);
    }
}
