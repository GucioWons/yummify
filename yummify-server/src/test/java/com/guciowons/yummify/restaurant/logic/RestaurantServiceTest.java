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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        Restaurant restaurant = buildRestaurantFromCreate(restaurantCreate);
        Restaurant savedRestaurant = cloneRestaurantWithId(restaurant);
        RestaurantDTO expectedResult = buildRestaurantDTO(savedRestaurant);

        UUID ownerId = UUID.randomUUID();

        when(restaurantMapper.mapToEntity(restaurantCreate)).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(savedRestaurant);
        when(authService.createUserAndGetId(restaurantCreate.owner())).thenReturn(ownerId);
        when(restaurantRepository.save(savedRestaurant)).thenReturn(savedRestaurant);
        when(restaurantMapper.mapToDTO(savedRestaurant)).thenReturn(expectedResult);

        RestaurantDTO result = underTest.create(restaurantCreate);

        assertEquals(savedRestaurant.getId().toString(), restaurantCreate.owner().getAttributes().get("restaurantId"));
        assertEquals(ownerId, savedRestaurant.getOwnerId());
        assertEquals(expectedResult, result);

        verify(restaurantRepository).save(restaurant);
        verify(restaurantRepository).save(savedRestaurant);
        verify(authService).createUserAndGetId(restaurantCreate.owner());
    }

    @Test
    public void shouldGetRestaurant() {
        Restaurant restaurant = buildRestaurant(UUID.randomUUID(), UUID.randomUUID(), "Pasta palace", "This is pasta palace");
        RestaurantDTO expectedResult = buildRestaurantDTO(restaurant);

        when(restaurantRepository.findById(restaurant.getId()))
                .thenReturn(Optional.of(restaurant));
        when(restaurantMapper.mapToDTO(restaurant))
                .thenReturn(expectedResult);

        RestaurantDTO result = underTest.getById(restaurant.getId());

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotGetRestaurantAndThrowExceptionWhenRestaurantNotFound() {
        UUID restaurantId = UUID.randomUUID();

        when(restaurantRepository.findById(restaurantId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> underTest.getById(restaurantId));

        verify(restaurantMapper, never()).mapToDTO(any());
    }

    @Test
    public void shouldUpdateRestaurant() {
        Restaurant toUpdate = buildRestaurant(UUID.randomUUID(), UUID.randomUUID(), "Pasta palace", "This is pasta palace");
        RestaurantDTO dto = new RestaurantDTO(null, "Pizza world", "This is pizza world");
        Restaurant toSave = buildRestaurantFromDTO(dto);
        Restaurant afterUpdate = buildRestaurant(toUpdate.getId(), toUpdate.getOwnerId(), dto.name(), dto.description());
        RestaurantDTO expectedResult = buildRestaurantDTO(afterUpdate);

        when(restaurantRepository.findById(toUpdate.getId()))
                .thenReturn(Optional.of(toUpdate));
        when(restaurantMapper.mapToUpdateEntity(dto, toUpdate))
                .thenReturn(toSave);
        when(restaurantRepository.save(toSave))
                .thenReturn(afterUpdate);
        when(restaurantMapper.mapToDTO(afterUpdate))
                .thenReturn(expectedResult);

        RestaurantDTO result = underTest.update(toUpdate.getId(), dto);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateRestaurantAndThrowExceptionWhenRestaurantNotFound() {
        UUID restaurantId = UUID.randomUUID();
        RestaurantDTO dto = new RestaurantDTO(null, "Pizza world", "This is pizza world");

        when(restaurantRepository.findById(restaurantId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> underTest.update(restaurantId, dto));

        verify(restaurantRepository, never()).save(any());
        verify(restaurantMapper, never()).mapToUpdateEntity(any(), any());
        verify(restaurantMapper, never()).mapToDTO(any());
    }

    private RestaurantCreateDTO buildRestaurantCreate() {
        return new RestaurantCreateDTO("Pasta palace", "This is pasta palace", buildUserRequest());
    }

    private Restaurant buildRestaurantFromDTO(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.name());
        restaurant.setDescription(dto.description());
        return restaurant;
    }

    private Restaurant buildRestaurantFromCreate(RestaurantCreateDTO restaurantCreate) {
        return buildRestaurant(null, null, restaurantCreate.name(), restaurantCreate.description());
    }

    private Restaurant cloneRestaurantWithId(Restaurant restaurant) {
        return buildRestaurant(UUID.randomUUID(), null, restaurant.getName(), restaurant.getDescription());
    }

    private Restaurant buildRestaurant(UUID id, UUID ownerId, String name, String description) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setOwnerId(ownerId);
        restaurant.setName(name);
        restaurant.setDescription(description);
        return restaurant;
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