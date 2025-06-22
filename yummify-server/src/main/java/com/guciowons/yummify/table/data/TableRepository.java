package com.guciowons.yummify.table.data;

import com.guciowons.yummify.table.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<Table, UUID> {
    boolean existsByNameAndRestaurantId(String name, UUID restaurantId);
}
