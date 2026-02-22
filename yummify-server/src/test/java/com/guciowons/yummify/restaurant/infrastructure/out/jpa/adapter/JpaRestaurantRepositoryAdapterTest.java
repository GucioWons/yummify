package com.guciowons.yummify.restaurant.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.JpaRestaurant;
import com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.mapper.JpaRestaurantMapper;
import com.guciowons.yummify.restaurant.infrastructure.out.jpa.repository.JpaRestaurantRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurant;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaRestaurantRepositoryAdapterTest {
    private final JpaRestaurantRepository jpaRestaurantRepository = mock(JpaRestaurantRepository.class);
    private final JpaRestaurantMapper jpaRestaurantMapper = mock(JpaRestaurantMapper.class);
    private final JpaRestaurantRepositoryAdapter underTest = new JpaRestaurantRepositoryAdapter(
            jpaRestaurantRepository,
            jpaRestaurantMapper
    );

    @Test
    void shouldSaveRestaurant() {
        // given
        var restaurant = givenRestaurant(1);
        var jpaRestaurant = new JpaRestaurant();

        when(jpaRestaurantMapper.toJpa(restaurant)).thenReturn(jpaRestaurant);

        // when
        underTest.save(restaurant);

        // then
        verify(jpaRestaurantMapper).toJpa(restaurant);
        verify(jpaRestaurantRepository).save(jpaRestaurant);
    }

    @Test
    void shouldFindRestaurantById() {
        // given
        var restaurantId = givenRestaurantId(1);
        var jpaRestaurant = new JpaRestaurant();
        var restaurant = givenRestaurant(1);

        when(jpaRestaurantRepository.findById(restaurantId.value())).thenReturn(Optional.of(jpaRestaurant));
        when(jpaRestaurantMapper.toDomain(jpaRestaurant)).thenReturn(restaurant);

        // when
        var result = underTest.findById(restaurantId);

        // then
        verify(jpaRestaurantRepository).findById(restaurantId.value());
        verify(jpaRestaurantMapper).toDomain(jpaRestaurant);

        assertThat(result).hasValue(restaurant);
    }
}
