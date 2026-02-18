package com.guciowons.yummify.dish.infrastructure.out.jpa.repository;

import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.JpaDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaDishRepository extends JpaRepository<JpaDish, UUID> {
    Optional<JpaDish> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    List<JpaDish> findAllByRestaurantId(UUID restaurantId);

    @Query("""
       select d.id
       from JpaDish d
       where d.id in :ids
       and d.restaurantId = :restaurantId
       """)
    Set<UUID> findExistingIdsByRestaurantId(List<UUID> ids, UUID restaurantId);

}



