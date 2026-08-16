package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateOrderUsecase {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderRepository orderRepository;

    public Order create(CreateOrderCommand command) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(command.userId(), command.restaurantId().value()));

        Order order = Order.create(command.restaurantId(), tableId);
        orderRepository.save(order);
        return order;
    }
}
