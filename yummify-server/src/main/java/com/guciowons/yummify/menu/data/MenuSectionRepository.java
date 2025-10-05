package com.guciowons.yummify.menu.data;

import com.guciowons.yummify.menu.entity.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, UUID> {
    Optional<MenuSection> findByIdAndMenuRestaurantId(UUID id, UUID restaurantId);
}
