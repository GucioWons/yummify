package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    public DishDTO create(DishDTO dto) {
        Dish entity = dishRepository.save(dishMapper.mapToEntity(dto));
        return dishMapper.mapToDTO(entity);
    }
}
