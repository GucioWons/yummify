package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableGetUsecase {
    private final TableRepository tableRepository;

    public Table get(UUID id, UUID restaurantId) {
        return tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new TableNotFoundException(id));
    }
}
