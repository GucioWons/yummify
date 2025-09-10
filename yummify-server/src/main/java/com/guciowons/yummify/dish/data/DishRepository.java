package com.guciowons.yummify.dish.data;

import com.guciowons.yummify.common.temp.repository.RestaurantScopedRepository;
import com.guciowons.yummify.dish.entity.Dish;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends RestaurantScopedRepository<Dish> {
}



