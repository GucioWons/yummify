package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.dish.DishContract;
import com.guciowons.yummify.dish.PublicDishFacadePort;
import com.guciowons.yummify.menu.PublicMenuFacadePort;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenAddOrderItemCommand;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrder;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderItemDishSnapshot;
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
        var order = givenOrder(1);
        var dishSnapshot = givenOrderItemDishSnapshot(1);
        var dishContract = DishContract.of(dishSnapshot.name());

        when(orderLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(order);
        when(publicDishFacadePort.get(command.dishId().value(), command.restaurantId().value())).thenReturn(dishContract);
        when(publicMenuFacadePort.getPriceByDishId(command.restaurantId().value(), command.dishId().value()))
                .thenReturn(dishSnapshot.price());

        // when
        var result = underTest.addItem(command);

        // then
        verify(orderLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(publicDishFacadePort).get(command.dishId().value(), command.restaurantId().value());
        verify(publicMenuFacadePort).getPriceByDishId(command.restaurantId().value(), command.dishId().value());
        verify(orderRepository).save(order);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getDishId()).isEqualTo(command.dishId());
        assertThat(result.getDishSnapshot()).isEqualTo(dishSnapshot);
        assertThat(result.getQuantity()).isEqualTo(1);
    }
}