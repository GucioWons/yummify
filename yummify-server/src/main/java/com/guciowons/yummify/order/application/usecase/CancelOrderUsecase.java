package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.CancelOrderCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CancelOrderUsecase {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;

    public Order cancel(CancelOrderCommand command) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(command.userId(), command.restaurantId().value()));
        Order order = orderLookupService.getByTableIdAndRestaurantId(tableId, command.restaurantId());
        order.cancel();

        orderRepository.save(order);
        return order;
    }
}
