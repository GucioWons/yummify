package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableGetAllUsecase {
    private final TableRepository tableRepository;

    public List<Table> getAll(UUID restaurantId) {
        return tableRepository.findAllByRestaurantId(restaurantId);
    }
}
