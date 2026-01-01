package com.guciowons.yummify.restaurant.infrastructure.repository;

import com.guciowons.yummify.restaurant.domain.port.RestaurantRepositoryPort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends RestaurantRepositoryPort, JpaRepository<Restaurant, UUID> {
}
