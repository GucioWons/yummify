package com.guciowons.yummify.order.infrastructure.out.jpa.repository;

import com.guciowons.yummify.order.domain.entity.OrderStatus;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.JpaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<JpaOrder, UUID> {
    Optional<JpaOrder> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    Optional<JpaOrder> findByTableIdAndRestaurantId(UUID tableId, UUID restaurantId);

    List<JpaOrder> findAllByStatusInAndRestaurantId(List<OrderStatus> statuses, UUID restaurantId);
}
