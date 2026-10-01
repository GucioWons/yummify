package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAccessService {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderRepository orderRepository;

    public boolean canAccess(Order.Id id, UserPrincipal user) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(user.id(), user.restaurantId()));
        return orderRepository.existsByIdAndTableId(id, tableId);
    }
}
