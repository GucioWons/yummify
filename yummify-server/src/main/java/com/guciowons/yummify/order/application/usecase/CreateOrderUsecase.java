package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.event.OrderCreatedEvent;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Usecase
@RequiredArgsConstructor
public class CreateOrderUsecase {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Order create(CreateOrderCommand command) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(command.userId(), command.restaurantId().value()));

        Order order = Order.create(command.restaurantId(), tableId);
        orderRepository.save(order);

        applicationEventPublisher.publishEvent(OrderCreatedEvent.of(order));

        return order;
    }
}
