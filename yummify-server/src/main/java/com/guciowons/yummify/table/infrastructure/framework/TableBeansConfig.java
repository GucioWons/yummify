package com.guciowons.yummify.table.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.table.application.TableFacade;
import com.guciowons.yummify.table.application.port.TableFacadePort;
import com.guciowons.yummify.table.infrastructure.decorator.rest.TableFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

@ComponentScan(
        basePackages = "com.guciowons.yummify.table",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class TableBeansConfig {
    @Bean
    @Primary
    TableFacadePort tableFacadePort(TableFacade tableFacade) {
        return new TableFacadeApiExceptionDecorator(tableFacade);
    }
}
