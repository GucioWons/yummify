package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.dish.DishContract;
import com.guciowons.yummify.dish.PublicDishFacadePort;
import com.guciowons.yummify.menu.PublicMenuFacadePort;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenAddOrderItemCommand;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AddOrderItemUsecaseTest {
    private final OrderLookupService orderLookupService = mock(OrderLookupService.class);
    private final PublicDishFacadePort publicDishFacadePort = mock(PublicDishFacadePort.class);
    private final PublicMenuFacadePort publicMenuFacadePort = mock(PublicMenuFacadePort.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private final AddOrderItemUsecase underTest = new AddOrderItemUsecase(
            orderLookupService,
            publicDishFacadePort,
            publicMenuFacadePort,
            orderRepository
    );

    @Test
    void shouldAddOrderItem() {
        // given
        var command = givenAddOrderItemCommand();
        var tableId = givenOrderTableId(1);
        var order = givenOrder(1);
        var dishSnapshot = givenOrderItemDishSnapshot(1);
        var dishContract = DishContract.of(dishSnapshot.name());

        when(orderLookupService.getByUserIdAndRestaurantId(tableId.value(), command.restaurantId())).thenReturn(order);
        when(publicDishFacadePort.get(command.dishId().value(), command.restaurantId().value())).thenReturn(dishContract);
        when(publicMenuFacadePort.getPriceByDishId(command.restaurantId().value(), command.dishId().value()))
                .thenReturn(dishSnapshot.price());

        // when
        var result = underTest.addItem(command);

        // then
        verify(orderLookupService).getByUserIdAndRestaurantId(tableId.value(), command.restaurantId());
        verify(publicDishFacadePort).get(command.dishId().value(), command.restaurantId().value());
        verify(publicMenuFacadePort).getPriceByDishId(command.restaurantId().value(), command.dishId().value());
        verify(orderRepository).save(order);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getDishId()).isEqualTo(command.dishId());
        assertThat(result.getDishSnapshot()).isEqualTo(dishSnapshot);
        assertThat(result.getQuantity()).isEqualTo(1);
    }
}