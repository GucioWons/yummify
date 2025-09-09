package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.DishManageDTO;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Transactional
    public DishManageDTO create(DishManageDTO dto) {
        Dish entity = dishMapper.mapToEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        return dishMapper.mapToAdminDTO(dishRepository.save(entity));
    }

    public List<DishClientDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findAllByRestaurantId(restaurantId).stream()
                .map(dishMapper::mapToClientDTO)
                .toList();
    }

    public DishManageDTO getById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(dishMapper::mapToAdminDTO)
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    @Transactional
    public DishManageDTO update(UUID id, DishManageDTO dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredient -> dishMapper.mapToAdminDTO(dishMapper.mapToUpdateEntity(dto, ingredient)))
                .orElseThrow(() -> new DishNotFoundException(id));
    }
}
