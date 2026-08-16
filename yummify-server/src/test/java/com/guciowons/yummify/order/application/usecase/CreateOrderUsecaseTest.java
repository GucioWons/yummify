package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenCreateOrderCommand;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateOrderUsecaseTest {
    private final PublicTableFacadePort publicTableFacadePort = mock(PublicTableFacadePort.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final CreateOrderUsecase underTest = new CreateOrderUsecase(publicTableFacadePort, orderRepository);

    @Test
    void shouldCreateOrder() {
        // given
        var command = givenCreateOrderCommand();
        var tableId = givenOrderTableId(1);

        when(publicTableFacadePort.getTableIdByUserId(command.userId(), command.restaurantId().value()))
                .thenReturn(tableId.value());

        // when
        var result = underTest.create(command);

        // then
        verify(publicTableFacadePort).getTableIdByUserId(command.userId(), command.restaurantId().value());
        verify(orderRepository).save(any(Order.class));

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getTableId()).isEqualTo(tableId);
        assertThat(result.getItems()).isNotNull().isEmpty();
        assertThat(result.getStatus()).isEqualTo(OrderStatus.NEW);
    }

}