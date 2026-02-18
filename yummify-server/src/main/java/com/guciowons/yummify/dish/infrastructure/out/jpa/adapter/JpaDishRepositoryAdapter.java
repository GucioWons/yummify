package com.guciowons.yummify.dish.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.mapper.JpaDishMapper;
import com.guciowons.yummify.dish.infrastructure.out.jpa.repository.JpaDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaDishRepositoryAdapter implements DishRepository {
    private final JpaDishRepository jpaDishRepository;
    private final JpaDishMapper jpaDishMapper;


    @Override
    public void save(Dish dish) {
        jpaDishRepository.save(jpaDishMapper.toJpa(dish));
    }

    @Override
    public Optional<Dish> findByIdAndRestaurantId(Dish.Id id, Dish.RestaurantId restaurantId) {
        return jpaDishRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaDishMapper::toDomain);
    }

    @Override
    public List<Dish> findAllByRestaurantId(Dish.RestaurantId restaurantId) {
        return jpaDishRepository.findAllByRestaurantId(restaurantId.value()).stream()
                .map(jpaDishMapper::toDomain)
                .toList();
    }
}
