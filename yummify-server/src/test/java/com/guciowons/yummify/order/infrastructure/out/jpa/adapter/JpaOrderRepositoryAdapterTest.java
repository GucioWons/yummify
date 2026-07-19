package com.guciowons.yummify.order.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.order.infrastructure.out.jpa.entity.JpaOrder;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.mapper.JpaOrderMapper;
import com.guciowons.yummify.order.infrastructure.out.jpa.repository.JpaOrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaOrderRepositoryAdapterTest {
    private final JpaOrderMapper jpaOrderMapper = mock(JpaOrderMapper.class);
    private final JpaOrderRepository jpaOrderRepository = mock(JpaOrderRepository.class);

    private final JpaOrderRepositoryAdapter underTest = new JpaOrderRepositoryAdapter(jpaOrderMapper, jpaOrderRepository);

    @Test
    void shouldSaveOrder() {
        // given
        var order = givenOrder(1);
        var jpaOrder = new JpaOrder();

        when(jpaOrderMapper.toJpa(order)).thenReturn(jpaOrder);

        // when
        underTest.save(order);

        // then
        verify(jpaOrderMapper).toJpa(order);
        verify(jpaOrderRepository).save(jpaOrder);
    }

    @Test
    void shouldFindByIdAndRestaurantId() {
        // given
        var orderId = givenOrderId(1);
        var restaurantId = givenOrderRestaurantId(1);
        var jpaOrder = new JpaOrder();
        var order = givenOrder(1);

        when(jpaOrderRepository.findByIdAndRestaurantId(orderId.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaOrder));
        when(jpaOrderMapper.toDomain(jpaOrder)).thenReturn(order);

        // when
        var result = underTest.findByIdAndRestaurantId(orderId, restaurantId);

        // then
        verify(jpaOrderRepository).findByIdAndRestaurantId(orderId.value(), restaurantId.value());
        verify(jpaOrderMapper).toDomain(jpaOrder);

        assertThat(result).hasValue(order);
    }

}