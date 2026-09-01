package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.order.application.command.RemoveOrderItemCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenUserId;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RemoveOrderItemUsecaseTest {
    private final OrderLookupService orderLookupService = mock(OrderLookupService.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final RemoveOrderItemUsecase underTest = new RemoveOrderItemUsecase(
            orderLookupService,
            orderRepository
    );

    @Test
    void shouldRemoveOrderItemAndSaveOrder() {
        // given
        var tableId = givenOrderTableId(1);
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 2);
        var command = new RemoveOrderItemCommand(givenUserId(), givenOrderRestaurantId(1), item.getId());

        when(orderLookupService.getByUserIdAndRestaurantId(tableId.value(), command.restaurantId())).thenReturn(order);

        // when
        underTest.removeOrderItem(command);

        // then
        verify(orderLookupService).getByUserIdAndRestaurantId(tableId.value(), command.restaurantId());
        verify(orderRepository).save(order);

        assertThat(order.getItems()).isEmpty();
    }
}