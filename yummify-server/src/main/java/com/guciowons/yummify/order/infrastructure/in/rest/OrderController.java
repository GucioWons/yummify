package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.model.OrderClientDto;
import com.guciowons.yummify.order.infrastructure.model.OrderItemClientDto;
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

    @PostMapping("{id}/complete")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderClientDto> complete(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id
    ) {
        Order order = orderFacade.complete(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toClientDto(order));
    }

    @GetMapping("current")
    @SecuredByPermission(Permission.ORDER_READ)
    public ResponseEntity<List<OrderClientDto>> getCurrent(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderClientDto> result = orderFacade.getCurrent(userPrincipal.restaurantId()).stream()
                .map(orderMapper::toClientDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("old")
    @SecuredByPermission(Permission.ORDER_READ)
    public ResponseEntity<List<OrderClientDto>> getOld(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderClientDto> result = orderFacade.getOld(userPrincipal.restaurantId()).stream()
                .map(orderMapper::toClientDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
