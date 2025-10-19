package com.guciowons.yummify.menu.data;

import com.guciowons.yummify.menu.entity.MenuEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuEntryRepository extends JpaRepository<MenuEntry, UUID> {
    Optional<MenuEntry> findByIdAndSectionMenuRestaurantId(UUID id, UUID restaurantId);
}
