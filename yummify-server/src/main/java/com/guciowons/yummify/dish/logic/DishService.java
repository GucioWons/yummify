package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.DishDTO;
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
    public DishDTO<TranslatedStringDTO> create(DishDTO<TranslatedStringDTO> dto) {
        Dish entity = dishMapper.mapToEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        return dishMapper.mapToAdminDTO(dishRepository.save(entity));
    }

    public List<DishDTO<String>> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findAllByRestaurantId(restaurantId).stream()
                .map(dishMapper::mapToClientDTO)
                .toList();
    }

    public DishDTO<TranslatedStringDTO> getById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(dishMapper::mapToAdminDTO)
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    @Transactional
    public DishDTO<TranslatedStringDTO> update(UUID id, DishDTO<TranslatedStringDTO> dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredient -> dishMapper.mapToAdminDTO(dishMapper.mapToUpdateEntity(dto, ingredient)))
                .orElseThrow(() -> new DishNotFoundException(id));
    }
}
