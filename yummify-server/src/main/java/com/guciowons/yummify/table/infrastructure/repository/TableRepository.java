package com.guciowons.yummify.table.infrastructure.repository;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.port.TableRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableRepository extends TableRepositoryPort, JpaRepository<Table, UUID> {
    boolean existsByNameAndRestaurantId(String name, UUID restaurantId);
}
