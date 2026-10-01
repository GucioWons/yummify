package com.guciowons.yummify.order.infrastructure.out.ws;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.event.OrderCreatedEvent;
import com.guciowons.yummify.order.domain.event.OrderUpdatedEvent;
import com.guciowons.yummify.order.infrastructure.out.ws.message.OrderWebSocketMessage;
import com.guciowons.yummify.order.infrastructure.out.ws.message.OrderWebSocketMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderWebSocketEventListener {
    private final OrderWebSocketMessageMapper orderWebSocketMessageMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Async
    @EventListener
    public void handle(OrderCreatedEvent event) {
        sendToRestaurant(event.order(), orderWebSocketMessageMapper.map(event));
    }

    @Async
    @EventListener
    public void handle(OrderUpdatedEvent event) {
        OrderWebSocketMessage message = orderWebSocketMessageMapper.map(event);

        sendToRestaurant(event.order(), message);
        sendToOrder(event.order(), message);
    }

    private void sendToRestaurant(Order order, OrderWebSocketMessage message) {
        UUID restaurantId = order.getRestaurantId().value();
        String destination = OrderWebSocketDestination.ORDERS.build(restaurantId.toString());
        messagingTemplate.convertAndSend(destination, message);
    }

    private void sendToOrder(Order order, OrderWebSocketMessage message) {
        UUID orderId = order.getId().value();
        String destination = OrderWebSocketDestination.ORDER_UPDATES.build(orderId.toString());
        messagingTemplate.convertAndSend(destination, message);
    }
}
