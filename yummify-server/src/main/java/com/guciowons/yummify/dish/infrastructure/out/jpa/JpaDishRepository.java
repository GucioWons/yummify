package com.guciowons.yummify.dish.infrastructure.out.jpa;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaDishRepository extends DishRepository, JpaRepository<Dish, UUID> {
}



