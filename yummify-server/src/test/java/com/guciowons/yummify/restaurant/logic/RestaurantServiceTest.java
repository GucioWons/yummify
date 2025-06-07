package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.restaurant.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {
    @InjectMocks
    private RestaurantService underTest;

    @Mock
    private PublicAuthService authService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @Test
    void shouldCreateRestaurant() {
        RestaurantCreateDTO restaurantCreate = buildRestaurantCreate();
        Restaurant restaurant = buildRestaurant(restaurantCreate);
        Restaurant savedRestaurant = cloneRestaurantWithId(restaurant);
        RestaurantDTO mappedRestaurant = buildRestaurantDTO(savedRestaurant);

        UUID ownerId = UUID.randomUUID();

        when(restaurantMapper.mapToEntity(restaurantCreate)).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(savedRestaurant);
        when(authService.createUserAndGetId(restaurantCreate.owner())).thenReturn(ownerId);
        when(restaurantRepository.save(savedRestaurant)).thenReturn(savedRestaurant);
        when(restaurantMapper.mapToDTO(savedRestaurant)).thenReturn(mappedRestaurant);

        RestaurantDTO result = underTest.create(restaurantCreate);

        assertEquals(savedRestaurant.getId().toString(), restaurantCreate.owner().getAttributes().get("restaurantId"));
        assertEquals(ownerId, savedRestaurant.getOwnerId());
        assertEquals(mappedRestaurant, result);

        verify(restaurantRepository).save(restaurant);
        verify(restaurantRepository).save(savedRestaurant);
        verify(authService).createUserAndGetId(restaurantCreate.owner());
    }

    private RestaurantCreateDTO buildRestaurantCreate() {
        return new RestaurantCreateDTO("Pasta palace", "This is pasta palace", buildUserRequest());
    }

    private Restaurant buildRestaurant(RestaurantCreateDTO restaurantCreate) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantCreate.name());
        restaurant.setDescription(restaurantCreate.description());
        return restaurant;
    }

    private Restaurant cloneRestaurantWithId(Restaurant restaurant) {
        Restaurant clone = new Restaurant();
        clone.setId(UUID.randomUUID());
        clone.setName(restaurant.getName());
        clone.setDescription(restaurant.getDescription());
        return clone;
    }

    private RestaurantDTO buildRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getDescription());
    }

    private UserRequestDTO buildUserRequest() {
        return new UserRequestDTO(
                "owner@example.com",
                "restaurantOwner",
                "Jane",
                "Doe",
                new HashMap<>()
        );
    }
}