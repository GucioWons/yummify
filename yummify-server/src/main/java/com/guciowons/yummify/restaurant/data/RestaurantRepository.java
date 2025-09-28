package com.guciowons.yummify.restaurant.data;

import com.guciowons.yummify.common.core.repository.BaseEntityRepository;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends BaseEntityRepository<Restaurant> {
}
