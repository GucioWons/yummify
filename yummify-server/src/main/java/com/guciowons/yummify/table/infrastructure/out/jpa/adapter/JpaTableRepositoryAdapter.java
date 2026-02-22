package com.guciowons.yummify.table.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import com.guciowons.yummify.table.infrastructure.out.jpa.entity.mapper.JpaTableMapper;
import com.guciowons.yummify.table.infrastructure.out.jpa.repository.JpaTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaTableRepositoryAdapter implements TableRepository {
    private final JpaTableRepository jpaTableRepository;
    private final JpaTableMapper jpaTableMapper;


    @Override
    public void save(Table table) {
        jpaTableRepository.save(jpaTableMapper.toJpa(table));
    }

    @Override
    public Optional<Table> findByIdAndRestaurantId(Table.Id id, Table.RestaurantId restaurantId) {
        return jpaTableRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaTableMapper::toDomain);
    }

    @Override
    public List<Table> findAllByRestaurantId(Table.RestaurantId restaurantId) {
        return jpaTableRepository.findAllByRestaurantId(restaurantId.value()).stream()
                .map(jpaTableMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByNameAndRestaurantId(Table.Name name, Table.RestaurantId restaurantId) {
        return jpaTableRepository.existsByNameAndRestaurantId(name.value(), restaurantId.value());
    }
}
