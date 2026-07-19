package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.in.rest.model.AddOrderItemDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.CreateOrderDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.OrderClientDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.OrderItemClientDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.mapper.OrderItemMapper;
import com.guciowons.yummify.order.infrastructure.in.rest.model.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderFacadePort orderFacade;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    @SecuredByPermission(Permission.ORDER_CREATE)
    public ResponseEntity<OrderClientDto> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateOrderDto dto
    ) {
        Order order = orderFacade.create(userPrincipal.restaurantId(), dto.tableId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.toClientDto(order));
    }

    @PostMapping("{id}/items")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemClientDto> addItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @RequestBody AddOrderItemDto dto
    ) {
        OrderItem item = orderFacade.addItem(id, userPrincipal.restaurantId(), dto.dishId(), dto.quantity());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toOrderItemClientDto(item));
    }
}
