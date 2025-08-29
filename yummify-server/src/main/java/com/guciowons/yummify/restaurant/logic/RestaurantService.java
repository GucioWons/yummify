package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import com.guciowons.yummify.restaurant.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final PublicUserCreateService userCreateService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional
    public RestaurantDTO<TranslatedStringDTO> create(RestaurantCreateDTO dto) {
        Restaurant entity = restaurantRepository.save(restaurantMapper.mapToEntity(dto));

        dto.owner().setAttributes(Map.of("restaurantId", List.of(entity.getId().toString())));
        UUID ownerId = userCreateService.createUserWithPassword(dto.owner());
        entity.setOwnerId(ownerId);

        return restaurantMapper.mapToAdminDTO(entity);
    }

    public RestaurantDTO<String> getForClient() {
        Restaurant restaurant = getActiveRestaurant();
        return restaurantMapper.mapToClientDTO(restaurant);
    }

    public RestaurantDTO<TranslatedStringDTO> getForAdmin() {
        Restaurant restaurant = getActiveRestaurant();
        return restaurantMapper.mapToAdminDTO(restaurant);
    }

    public RestaurantDTO<TranslatedStringDTO> update(RestaurantDTO<TranslatedStringDTO> dto) {
        Restaurant updatedRestaurant = restaurantMapper.mapToUpdateEntity(dto, getActiveRestaurant());
        return restaurantMapper.mapToAdminDTO(updatedRestaurant);
    }

    private Restaurant getActiveRestaurant() {
        UUID id = RequestContext.get().getUser().getRestaurantId();
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
