package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.model.OrderDto;
import com.guciowons.yummify.order.infrastructure.model.OrderItemDto;
import com.guciowons.yummify.order.infrastructure.model.mapper.OrderItemMapper;
import com.guciowons.yummify.order.infrastructure.model.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderFacadePort orderFacade;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @PostMapping("{id}/items/{itemId}/start")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemDto> startPreparation(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.startPreparation(id, userPrincipal.id(), userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toDto(item));
    }

    @PostMapping("{id}/items/{itemId}/finish")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemDto> finishPreparation(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.finishPreparation(id, userPrincipal.id(), userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toDto(item));
    }

    @PostMapping("{id}/items/{itemId}/serve")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemDto> serve(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @PathVariable UUID itemId
    ) {
        OrderItem item = orderFacade.serve(id, userPrincipal.id(), userPrincipal.restaurantId(), itemId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toDto(item));
    }

    @PostMapping("{id}/complete")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderDto> complete(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id
    ) {
        Order order = orderFacade.complete(id, userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }

    @GetMapping("current")
    @SecuredByPermission(Permission.ORDER_READ)
    public ResponseEntity<List<OrderDto>> getCurrent(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderDto> result = orderFacade.getCurrent(userPrincipal.restaurantId()).stream()
                .map(orderMapper::toDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("old")
    @SecuredByPermission(Permission.ORDER_READ)
    public ResponseEntity<List<OrderDto>> getOld(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderDto> result = orderFacade.getOld(userPrincipal.restaurantId()).stream()
                .map(orderMapper::toDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
