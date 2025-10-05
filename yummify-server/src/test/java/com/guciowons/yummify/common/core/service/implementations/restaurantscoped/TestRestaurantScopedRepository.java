package com.guciowons.yummify.common.core.service.implementations.restaurantscoped;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TestRestaurantScopedRepository extends RestaurantScopedRepository<TestRestaurantScopedEntity> {
}
