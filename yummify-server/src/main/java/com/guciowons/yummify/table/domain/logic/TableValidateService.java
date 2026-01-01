package com.guciowons.yummify.table.domain.logic;

import com.guciowons.yummify.table.exception.TableExistsByNameException;
import com.guciowons.yummify.table.infrastructure.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableValidateService {
    private final TableRepository tableRepository;

    public void checkNameUnique(String name, UUID restaurantId) {
        if (tableRepository.existsByNameAndRestaurantId(name, restaurantId)) {
            throw new TableExistsByNameException(name);
        }
    }
}
