package com.guciowons.yummify.restaurant.infrastructure.out.jpa;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRestaurantRepository extends RestaurantRepository, JpaRepository<Restaurant, RestaurantId> {
}
