package com.guciowons.yummify.restaurant.infrastructure.out.jpa.repository;

import com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.JpaRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaRestaurantRepository extends JpaRepository<JpaRestaurant, UUID> {
}
