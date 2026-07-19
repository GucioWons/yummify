package com.guciowons.yummify.order.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.mapper.JpaOrderMapper;
import com.guciowons.yummify.order.infrastructure.out.jpa.repository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderMapper jpaOrderMapper;
    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public void save(Order order) {
        jpaOrderRepository.save(jpaOrderMapper.toJpa(order));
    }

    @Override
    public Optional<Order> findByIdAndRestaurantId(Order.Id id, Order.RestaurantId restaurantId) {
        return jpaOrderRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaOrderMapper::toDomain);
    }
}
