package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.security.logic.TokenService;
import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.exception.DishExistsByNameException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final TokenService tokenService;
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    public DishDTO create(DishDTO dto) {
        UUID restaurantId = tokenService.getRestaurantId();
        if (dishRepository.existsByNameAndRestaurantId(dto.name(), restaurantId)) {
            throw new DishExistsByNameException(dto.name());
        }

        Dish entity = dishRepository.save(dishMapper.mapToEntity(dto));
        return dishMapper.mapToDTO(entity);
    }
}
