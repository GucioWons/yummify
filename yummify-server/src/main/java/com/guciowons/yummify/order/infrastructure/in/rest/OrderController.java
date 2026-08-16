package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.in.rest.model.AddOrderItemDto;
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
    public ResponseEntity<OrderClientDto> create(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.create(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.toClientDto(order));
    }

    @PostMapping("items")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemClientDto> addItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody AddOrderItemDto dto
    ) {
        OrderItem item = orderFacade.addItem(userPrincipal.id(), userPrincipal.restaurantId(), dto.dishId(), dto.quantity());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toOrderItemClientDto(item));
    }

    @DeleteMapping("items/{itemId}")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<Void> removeOrderItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID itemId
    ) {
        orderFacade.removeItem(userPrincipal.id(), userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("submit")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderClientDto> submit(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.submit(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toClientDto(order));
    }

    @PostMapping("cancel")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderClientDto> cancel(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.cancel(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toClientDto(order));
    }

    @PostMapping("{id}/items/{itemId}/start")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemClientDto> startPreparation(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.startPreparation(id, userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toOrderItemClientDto(item));
    }

    @PostMapping("{id}/items/{itemId}/finish")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemClientDto> finishPreparation(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.finishPreparation(id, userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toOrderItemClientDto(item));
    }

    @PostMapping("{id}/items/{itemId}/serve")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemClientDto> serve(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.serve(id, userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toOrderItemClientDto(item));
    }
}
