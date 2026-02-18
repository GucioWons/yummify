package com.guciowons.yummify.dish.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.JpaDish;
import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.mapper.JpaDishMapper;
import com.guciowons.yummify.dish.infrastructure.out.jpa.repository.JpaDishRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaDishRepositoryAdapterTest {
    private final JpaDishRepository jpaDishRepository = mock(JpaDishRepository.class);
    private final JpaDishMapper jpaDishMapper = mock(JpaDishMapper.class);

    private final JpaDishRepositoryAdapter underTest = new JpaDishRepositoryAdapter(jpaDishRepository, jpaDishMapper);

    @Test
    void shouldSaveDish() {
        // given
        var dish = givenDish(1);
        var jpaDish = new JpaDish();

        when(jpaDishMapper.toJpa(dish)).thenReturn(jpaDish);

        // when
        underTest.save(dish);

        // then
        verify(jpaDishMapper).toJpa(dish);
        verify(jpaDishRepository).save(jpaDish);
    }

    @Test
    void shouldFindDish() {
        // given
        var id = givenDishId(1);
        var restaurantId = givenDishRestaurantId(1);
        var jpaDish = new JpaDish();
        var dish = givenDish(1);

        when(jpaDishRepository.findByIdAndRestaurantId(id.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaDish));
        when(jpaDishMapper.toDomain(jpaDish)).thenReturn(dish);

        // when
        var result = underTest.findByIdAndRestaurantId(id, restaurantId);

        // then
        verify(jpaDishRepository).findByIdAndRestaurantId(id.value(), restaurantId.value());
        verify(jpaDishMapper).toDomain(jpaDish);

        assertThat(result).hasValue(dish);
    }

    @Test
    void shouldFindAllDishes() {
        // given
        var restaurantId = givenDishRestaurantId(1);
        var jpaDishes = List.of(new JpaDish(), new JpaDish(), new JpaDish());
        var dishes = List.of(givenDish(1), givenDish(2), givenDish(3));

        when(jpaDishRepository.findAllByRestaurantId(restaurantId.value())).thenReturn(jpaDishes);
        when(jpaDishMapper.toDomain(jpaDishes.getFirst())).thenReturn(dishes.getFirst());
        when(jpaDishMapper.toDomain(jpaDishes.get(1))).thenReturn(dishes.get(1));
        when(jpaDishMapper.toDomain(jpaDishes.get(2))).thenReturn(dishes.get(2));

        // when
        var result = underTest.findAllByRestaurantId(restaurantId);

        // then
        verify(jpaDishRepository).findAllByRestaurantId(restaurantId.value());
        verify(jpaDishMapper).toDomain(jpaDishes.getFirst());
        verify(jpaDishMapper).toDomain(jpaDishes.get(1));
        verify(jpaDishMapper).toDomain(jpaDishes.get(2));

        assertThat(result).isEqualTo(dishes);
    }
}