package com.guciowons.yummify.order.infrastructure.framework;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.OrderFacade;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.infrastructure.decorator.rest.OrderFacadeApiExceptionDecorator;
import org.springframework.context.annotation.*;

@ComponentScan(
        basePackages = "com.guciowons.yummify.order",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Usecase.class, Facade.class, ExceptionMapper.class, ApplicationService.class}
                )
        }
)
@Configuration
public class OrderBeansConfig {
    @Bean
    @Primary
    OrderFacadePort orderFacadePort(OrderFacade orderFacade) {
        return new OrderFacadeApiExceptionDecorator(orderFacade);
    }
}
