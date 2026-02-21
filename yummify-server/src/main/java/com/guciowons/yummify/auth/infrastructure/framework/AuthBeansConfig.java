package com.guciowons.yummify.auth.infrastructure.framework;

import com.guciowons.yummify.auth.application.exception.AuthExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

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
    public DomainExceptionHandler authDomainExceptionHandler(
            AuthExceptionMapper authExceptionMapper
    ) {
        return new DomainExceptionHandler(authExceptionMapper);
    }
}
