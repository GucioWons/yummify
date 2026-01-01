package com.guciowons.yummify.dish.infractructure.dish.repository;

import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import com.guciowons.yummify.dish.domain.dish.port.DishRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DishRepository extends DishRepositoryPort, JpaRepository<Dish, UUID> {
}



