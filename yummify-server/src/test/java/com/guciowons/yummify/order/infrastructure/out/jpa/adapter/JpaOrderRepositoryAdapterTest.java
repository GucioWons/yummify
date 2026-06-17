package com.guciowons.yummify.order.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.order.infrastructure.out.jpa.entity.JpaOrder;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.mapper.JpaOrderMapper;
import com.guciowons.yummify.order.infrastructure.out.jpa.repository.JpaOrderRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrder;
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

}