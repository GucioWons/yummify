package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.SubmitOrderCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class SubmitOrderUsecase {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;

    public Order submit(SubmitOrderCommand command) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(command.userId(), command.restaurantId().value()));
        Order order = orderLookupService.getByTableIdAndRestaurantId(tableId, command.restaurantId());
        order.submit();
        orderRepository.save(order);
        return order;
    }
}
