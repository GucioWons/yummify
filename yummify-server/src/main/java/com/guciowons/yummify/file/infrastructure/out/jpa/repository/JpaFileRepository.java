package com.guciowons.yummify.file.infrastructure.out.jpa.repository;

import com.guciowons.yummify.file.infrastructure.out.jpa.entity.JpaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaFileRepository extends JpaRepository<JpaFile, UUID> {
    Optional<JpaFile> findByIdAndRestaurantId(UUID id, UUID restaurantId);
}
