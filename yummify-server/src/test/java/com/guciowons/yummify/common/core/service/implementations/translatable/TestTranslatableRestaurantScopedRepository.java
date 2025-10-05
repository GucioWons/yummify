package com.guciowons.yummify.common.core.service.implementations.translatable;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TestTranslatableRestaurantScopedRepository extends RestaurantScopedRepository<TestTranslatableRestaurantScopedEntity> {
}
