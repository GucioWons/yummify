package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final KeycloakService keycloakService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantDTO create(RestaurantDTO dto) {
        keycloakService.createUserAndGetId("twojastara@wp.pl");
//        Restaurant entity = restaurantRepository.save(restaurantMapper.mapToEntity(dto));
//        return restaurantMapper.mapToDTO(entity);
    return null;
    }
}
