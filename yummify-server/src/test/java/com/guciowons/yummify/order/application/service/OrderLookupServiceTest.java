package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.order.domain.exception.OrderNotFoundException;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class OrderLookupServiceTest {
    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderLookupService underTest = new OrderLookupService(orderRepository);

    @Test
    void shouldGetOrder_WhenExists() {
        // given
        var order = givenOrder(1);
        var orderId = order.getId();
        var restaurantId = order.getRestaurantId();

        when(orderRepository.findByIdAndRestaurantId(orderId, restaurantId)).thenReturn(Optional.of(order));

        // when
        var result = underTest.getByIdAndRestaurantId(orderId, restaurantId);

        // then
        verify(orderRepository).findByIdAndRestaurantId(orderId, restaurantId);

        assertThat(result).isEqualTo(order);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var orderId = givenOrderId(1);
        var restaurantId = givenOrderRestaurantId(1);

        when(orderRepository.findByIdAndRestaurantId(orderId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(orderId, restaurantId))
                .isInstanceOf(OrderNotFoundException.class);

        // then
        verify(orderRepository).findByIdAndRestaurantId(orderId, restaurantId);
    }
}