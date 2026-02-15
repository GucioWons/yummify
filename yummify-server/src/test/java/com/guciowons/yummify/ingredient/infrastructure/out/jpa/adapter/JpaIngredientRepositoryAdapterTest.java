package com.guciowons.yummify.ingredient.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.JpaIngredient;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.mapper.JpaIngredientMapper;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.repository.JpaIngredientRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaIngredientRepositoryAdapterTest {
    private final JpaIngredientRepository jpaIngredientRepository = mock(JpaIngredientRepository.class);
    private final JpaIngredientMapper jpaIngredientMapper = mock(JpaIngredientMapper.class);


    private final JpaIngredientRepositoryAdapter underTest = new JpaIngredientRepositoryAdapter(
            jpaIngredientRepository,
            jpaIngredientMapper
    );

    @Test
    void shouldSaveIngredient() {
        // given
        var ingredient = givenIngredient(1);
        var jpaIngredient = new JpaIngredient();

        when(jpaIngredientMapper.toJpa(ingredient)).thenReturn(jpaIngredient);

        // when
        underTest.save(ingredient);

        // then
        verify(jpaIngredientMapper).toJpa(ingredient);
        verify(jpaIngredientRepository).save(jpaIngredient);
    }

    @Test
    void shouldFindIngredient() {
        // given
        var id = givenIngredientId(1);
        var restaurantId = givenIngredientRestaurantId(1);
        var jpaIngredient = new JpaIngredient();
        var ingredient = givenIngredient(1);

        when(jpaIngredientRepository.findByIdAndRestaurantId(id.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaIngredient));
        when(jpaIngredientMapper.toDomain(jpaIngredient)).thenReturn(ingredient);

        // when
        var result = underTest.findByIdAndRestaurantId(id, restaurantId);

        // then
        verify(jpaIngredientRepository).findByIdAndRestaurantId(id.value(), restaurantId.value());
        verify(jpaIngredientMapper).toDomain(jpaIngredient);

        assertThat(result).hasValue(ingredient);
    }

    @Test
    void shouldFindAllIngredients() {
        // given
        var restaurantId = givenIngredientRestaurantId(1);
        var jpaIngredients = List.of(new JpaIngredient(), new JpaIngredient(), new JpaIngredient());
        var ingredients = List.of(givenIngredient(1), givenIngredient(2), givenIngredient(3));

        when(jpaIngredientRepository.findAllByRestaurantId(restaurantId.value())).thenReturn(jpaIngredients);
        when(jpaIngredientMapper.toDomain(jpaIngredients.getFirst())).thenReturn(ingredients.getFirst());
        when(jpaIngredientMapper.toDomain(jpaIngredients.get(1))).thenReturn(ingredients.get(1));
        when(jpaIngredientMapper.toDomain(jpaIngredients.get(2))).thenReturn(ingredients.get(2));

        // when
        var result = underTest.findAllByRestaurantId(restaurantId);

        // then
        verify(jpaIngredientRepository).findAllByRestaurantId(restaurantId.value());
        verify(jpaIngredientMapper).toDomain(jpaIngredients.getFirst());
        verify(jpaIngredientMapper).toDomain(jpaIngredients.get(1));
        verify(jpaIngredientMapper).toDomain(jpaIngredients.get(2));

        assertThat(result).isEqualTo(ingredients);
    }
}
