package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.order.application.service.OrderValidator;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenCreateOrderCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateOrderUsecaseTest {
    private final OrderValidator orderValidator = mock(OrderValidator.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final CreateOrderUsecase underTest = new CreateOrderUsecase(orderValidator, orderRepository);

    @Test
    void shouldCreateOrder() {
        // given
        var command = givenCreateOrderCommand();

        // when
        var result = underTest.create(command);

        // then
        verify(orderValidator).validate(command.tableId(), command.restaurantId());
        verify(orderRepository).save(any(Order.class));

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getTableId()).isEqualTo(command.tableId());
        assertThat(result.getItems()).isNotNull().isEmpty();
        assertThat(result.getStatus()).isEqualTo(OrderStatus.NEW);
    }

}