package com.guciowons.yummify.auth.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.mapper.JpaRoleMapper;
import com.guciowons.yummify.auth.infrastructure.out.jpa.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleRepository {
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaRoleMapper jpaRoleMapper;

    @Override
    public void save(Role role) {
        jpaRoleRepository.save(jpaRoleMapper.toJpa(role));
    }

    @Override
    public List<Role> findAllByRestaurantId(Role.RestaurantId restaurantId) {
        return jpaRoleRepository.findAllByRestaurantId(restaurantId.value()).stream()
                .map(jpaRoleMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Role> findByIdAndRestaurantId(Role.Id id, Role.RestaurantId restaurantId) {
        return jpaRoleRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaRoleMapper::toDomain);
    }
}
