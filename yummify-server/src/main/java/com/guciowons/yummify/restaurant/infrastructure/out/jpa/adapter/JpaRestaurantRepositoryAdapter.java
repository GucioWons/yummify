package com.guciowons.yummify.restaurant.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.mapper.JpaRestaurantMapper;
import com.guciowons.yummify.restaurant.infrastructure.out.jpa.repository.JpaRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRestaurantRepositoryAdapter implements RestaurantRepository {
    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final JpaRestaurantMapper jpaRestaurantMapper;

    @Override
    public void save(Restaurant restaurant) {
        jpaRestaurantRepository.save(jpaRestaurantMapper.toJpa(restaurant));
    }

    @Override
    public Optional<Restaurant> findById(Restaurant.Id id) {
        return jpaRestaurantRepository.findById(id.value())
                .map(jpaRestaurantMapper::toDomain);
    }
}
