package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.order.application.command.RemoveOrderItemCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RemoveOrderItemUsecaseTest {
    private final OrderLookupService orderLookupService = mock(OrderLookupService.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final RemoveOrderItemUsecase underTest = new RemoveOrderItemUsecase(orderLookupService, orderRepository);

    @Test
    void shouldRemoveOrderItemAndSaveOrder() {
        // given
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 2);
        var command = new RemoveOrderItemCommand(givenOrderId(1), givenOrderRestaurantId(1), item.getId());

        when(orderLookupService.getByIdAndRestaurantId(command.orderId(), command.restaurantId())).thenReturn(order);

        // when
        underTest.removeOrderItem(command);

        // then
        verify(orderLookupService).getByIdAndRestaurantId(command.orderId(), command.restaurantId());
        verify(orderRepository).save(order);

        assertThat(order.getItems()).isEmpty();
    }
}