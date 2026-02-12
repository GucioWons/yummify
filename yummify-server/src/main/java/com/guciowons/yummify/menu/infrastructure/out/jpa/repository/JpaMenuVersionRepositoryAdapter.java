package com.guciowons.yummify.menu.infrastructure.out.jpa.repository;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.mapper.JpaMenuVersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaMenuVersionRepositoryAdapter implements MenuVersionRepository {
    private final JpaMenuVersionMapper jpaMenuVersionMapper;
    private final JpaMenuVersionRepository jpaMenuVersionRepository;

    @Override
    public void save(MenuVersion menuVersion) {
        jpaMenuVersionRepository.save(jpaMenuVersionMapper.toJpa(menuVersion));
    }

    @Override
    public Optional<MenuVersion> findArchivedByIdAndRestaurantId(MenuVersion.Id id, MenuVersion.RestaurantId restaurantId) {
        return jpaMenuVersionRepository.findByIdAndRestaurantIdAndStatus(id.value(), restaurantId.value(), "ARCHIVED")
                .map(jpaMenuVersionMapper::toDomain);
    }

    @Override
    public Optional<MenuVersion> findDraftByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return jpaMenuVersionRepository.findByRestaurantIdAndStatus(restaurantId.value(), "DRAFT")
                .map(jpaMenuVersionMapper::toDomain);
    }

    @Override
    public Optional<MenuVersion> findPublishedByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return jpaMenuVersionRepository.findByRestaurantIdAndStatus(restaurantId.value(), "PUBLISHED")
                .map(jpaMenuVersionMapper::toDomain);
    }

    @Override
    public List<MenuVersion> findAllByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return jpaMenuVersionRepository.findAllByRestaurantId(restaurantId.value()).stream()
                .map(jpaMenuVersionMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByRestaurantId(MenuVersion.RestaurantId restaurantId) {
        return jpaMenuVersionRepository.existsByRestaurantId(restaurantId.value());
    }
}
