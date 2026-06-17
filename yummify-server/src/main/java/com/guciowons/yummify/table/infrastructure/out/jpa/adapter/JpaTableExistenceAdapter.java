package com.guciowons.yummify.table.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.table.TableExistencePort;
import com.guciowons.yummify.table.infrastructure.out.jpa.repository.JpaTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaTableExistenceAdapter implements TableExistencePort {
    private final JpaTableRepository jpaTableRepository;

    @Override
    public boolean exists(UUID tableId, UUID restaurantId) {
        return jpaTableRepository.existsByIdAndRestaurantId(tableId, restaurantId);
    }
}
