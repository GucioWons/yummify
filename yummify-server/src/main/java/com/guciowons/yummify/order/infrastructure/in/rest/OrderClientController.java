package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.model.AddOrderItemDto;
import com.guciowons.yummify.order.infrastructure.model.OrderDto;
import com.guciowons.yummify.order.infrastructure.model.OrderItemDto;
import com.guciowons.yummify.order.infrastructure.model.mapper.OrderItemMapper;
import com.guciowons.yummify.order.infrastructure.model.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("client/orders")
public class OrderClientController {
    private final OrderFacadePort orderFacade;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    @SecuredByPermission(Permission.ORDER_CREATE)
    public ResponseEntity<OrderDto> create(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.create(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.toDto(order));
    }

    @GetMapping
    @SecuredByPermission(Permission.ORDER_READ)
    public ResponseEntity<OrderDto> get(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.get(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }

    @PostMapping("items")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderItemDto> addItem(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody AddOrderItemDto dto
    ) {
        OrderItem item = orderFacade.addItem(userPrincipal.id(), userPrincipal.restaurantId(), dto.dishId(), dto.quantity());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderItemMapper.toDto(item));
    }

    @PostMapping("submit")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderDto> submit(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.submit(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }

    @PostMapping("cancel")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderDto> cancel(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.cancel(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }

    @PatchMapping("assistance")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderDto> requestAssistance(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.requestAssistance(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }

    @PatchMapping("payment")
    @SecuredByPermission(Permission.ORDER_MODIFY)
    public ResponseEntity<OrderDto> requestPayment(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Order order = orderFacade.requestPayment(userPrincipal.id(), userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(order));
    }
}
