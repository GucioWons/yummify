package com.guciowons.yummify.file.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.file.FileFacadePort;
import com.guciowons.yummify.file.application.FileFacade;
import com.guciowons.yummify.file.infrastructure.decorator.rest.FileFacadeApiExceptionDecorator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

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
    @Primary
    FileFacadePort fileFacadePort(FileFacade fileFacade) {
        return new FileFacadeApiExceptionDecorator(fileFacade);
    }
}
