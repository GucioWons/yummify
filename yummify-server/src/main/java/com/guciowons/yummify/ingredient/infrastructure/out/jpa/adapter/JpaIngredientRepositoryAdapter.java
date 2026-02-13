package com.guciowons.yummify.ingredient.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.mapper.JpaIngredientMapper;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.repository.JpaIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaIngredientRepositoryAdapter implements IngredientRepository {
    private final JpaIngredientRepository jpaIngredientRepository;
    private final JpaIngredientMapper jpaIngredientMapper;

    @Override
    public void save(Ingredient ingredient) {
        jpaIngredientRepository.save(jpaIngredientMapper.toJpa(ingredient));
    }

    @Override
    public Optional<Ingredient> findByIdAndRestaurantId(Ingredient.Id id, Ingredient.RestaurantId restaurantId) {
        return jpaIngredientRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaIngredientMapper::toDomain);
    }

    @Override
    public List<Ingredient> findAllByRestaurantId(Ingredient.RestaurantId restaurantId) {
        return jpaIngredientRepository.findAllByRestaurantId(restaurantId.value()).stream()
                .map(jpaIngredientMapper::toDomain)
                .toList();
    }

    @Override
    public List<Ingredient> findByIdInAndRestaurantId(List<UUID> ids, UUID restaurantId) {
        return jpaIngredientRepository.findByIdInAndRestaurantId(ids, restaurantId).stream()
                .map(jpaIngredientMapper::toDomain)
                .toList();
    }
}
