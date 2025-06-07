package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.common.security.logic.TokenService;
import com.guciowons.yummify.restaurant.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final TokenService tokenService;
    private final PublicAuthService authService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional
    public RestaurantDTO create(RestaurantCreateDTO dto) {
        Restaurant entity = restaurantRepository.save(restaurantMapper.mapToEntity(dto));

        dto.owner().setAttributes(Map.of("restaurantId", entity.getId().toString()));
        UUID ownerId = authService.createUserAndGetId(dto.owner());
        entity.setOwnerId(ownerId);

        return restaurantMapper.mapToDTO(restaurantRepository.save(entity));
    }

    public RestaurantDTO get() {
        return restaurantRepository.findById(tokenService.getRestaurantId())
                .map(restaurantMapper::mapToDTO)
                .orElseThrow();
    }

    public RestaurantDTO update(RestaurantDTO dto) {
        return restaurantRepository.findById(tokenService.getRestaurantId())
                .map(restaurant -> updateAndMap(dto, restaurant))
                .orElseThrow();
    }

    private RestaurantDTO updateAndMap(RestaurantDTO dto, Restaurant toUpdate) {
        Restaurant toSave = restaurantMapper.mapToUpdateEntity(dto, toUpdate);
        return restaurantMapper.mapToDTO(restaurantRepository.save(toSave));
    }
}
