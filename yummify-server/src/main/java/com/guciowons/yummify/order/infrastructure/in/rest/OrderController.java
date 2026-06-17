package com.guciowons.yummify.order.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.infrastructure.in.rest.model.CreateOrderDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.OrderClientDto;
import com.guciowons.yummify.order.infrastructure.in.rest.model.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderFacadePort orderFacade;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderClientDto> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateOrderDto dto
    ) {
        Order order = orderFacade.create(userPrincipal.restaurantId(), dto.tableId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.toClientDto(order));
    }
}
