package com.guciowons.yummify.order.infrastructure.out.jpa.repository;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.mapper.JpaOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderMapper jpaOrderMapper;
    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public void save(Order order) {
        jpaOrderRepository.save(jpaOrderMapper.toJpa(order));
    }
}
