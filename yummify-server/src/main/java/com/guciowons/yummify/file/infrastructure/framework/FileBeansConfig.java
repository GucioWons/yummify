package com.guciowons.yummify.file.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.file.application.exception.FileDomainExceptionMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.guciowons.yummify.file",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@EnableConfigurationProperties(MinioProperties.class)
public class FileBeansConfig {
    @Bean
    public DomainExceptionHandler fileDomainExceptionHandler(
            FileDomainExceptionMapper tableDomainExceptionMapper
    ) {
        return new DomainExceptionHandler(tableDomainExceptionMapper);
    }
}
