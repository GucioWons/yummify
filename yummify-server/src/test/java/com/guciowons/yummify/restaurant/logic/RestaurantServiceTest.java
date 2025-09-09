package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.restaurant.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantDTO;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import com.guciowons.yummify.restaurant.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {
//    @InjectMocks
//    private RestaurantService underTest;
//
//    @Mock
//    private PublicUserCreateService userCreateService;
//
//    @Mock
//    private RestaurantRepository restaurantRepository;
//
//    @Mock
//    private RestaurantMapper restaurantMapper;
//
//    @BeforeEach
//    void setUp() {
//        UserDTO mockUser = new UserDTO();
//        mockUser.setId(UUID.randomUUID());
//        mockUser.setRestaurantId(UUID.randomUUID());
//        mockUser.setUsername("testUser");
//
//        Language language = Language.ENGLISH;
//
//        RequestContext.set(new RequestContext(mockUser, language, language));
//    }
//
//    @Test
//    void shouldCreateRestaurant() {
//        RestaurantCreateDTO restaurantCreate = buildRestaurantCreate();
//        Restaurant restaurant = buildRestaurantFromCreate(restaurantCreate);
//        Restaurant savedRestaurant = cloneRestaurantWithId(restaurant);
//        RestaurantManageDTO expectedResult = buildRestaurantAdminDTO(savedRestaurant);
//
//        UUID ownerId = UUID.randomUUID();
//
//        when(restaurantMapper.mapToEntity(restaurantCreate)).thenReturn(restaurant);
//        when(restaurantRepository.save(restaurant)).thenReturn(savedRestaurant);
//        when(userCreateService.createUserWithPassword(restaurantCreate.owner())).thenReturn(ownerId);
//        when(restaurantMapper.mapToAdminDTO(savedRestaurant)).thenReturn(expectedResult);
//
//        RestaurantManageDTO result = underTest.create(restaurantCreate);
//
//        assertEquals(List.of(savedRestaurant.getId().toString()), restaurantCreate.owner().getAttributes().get("restaurantId"));
//        assertEquals(ownerId, savedRestaurant.getOwnerId());
//        assertEquals(expectedResult, result);
//
//        verify(restaurantRepository).save(restaurant);
//        verify(userCreateService).createUserWithPassword(restaurantCreate.owner());
//    }
//
//    @Test
//    public void shouldGetRestaurantForClient() {
//        Restaurant restaurant = buildRestaurant(UUID.randomUUID(), UUID.randomUUID(), "Pasta palace", "This is pasta palace");
//        RestaurantClientDTO expectedResult = buildRestaurantClientDTO(restaurant);
//
//        when(tokenService.getRestaurantId())
//                .thenReturn(restaurant.getId());
//        when(restaurantRepository.findById(restaurant.getId()))
//                .thenReturn(Optional.of(restaurant));
//        when(restaurantMapper.mapToClientDTO(restaurant))
//                .thenReturn(expectedResult);
//
//        RestaurantClientDTO result = underTest.getForClient();
//
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void shouldNotGetRestaurantForClientAndThrowExceptionWhenRestaurantNotFound() {
//        UUID restaurantId = UUID.randomUUID();
//
//        when(tokenService.getRestaurantId())
//                .thenReturn(restaurantId);
//        when(restaurantRepository.findById(restaurantId))
//                .thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> underTest.getForClient());
//
//        verify(restaurantMapper, never()).mapToClientDTO(any());
//    }
//
//    @Test
//    public void shouldUpdateRestaurant() {
//        Restaurant toUpdate = buildRestaurant(UUID.randomUUID(), UUID.randomUUID(), "Pasta palace", "This is pasta palace");
//        RestaurantManageDTO dto = new RestaurantDTO(null, "Pizza world", "This is pizza world");
//        Restaurant toSave = buildRestaurantFromDTO(dto);
//        Restaurant afterUpdate = buildRestaurant(toUpdate.getId(), toUpdate.getOwnerId(), dto.name(), dto.description());
//        RestaurantManageDTO expectedResult = buildRestaurantAdminDTO(afterUpdate);
//
//        when(tokenService.getRestaurantId())
//                .thenReturn(toUpdate.getId());
//        when(restaurantRepository.findById(toUpdate.getId()))
//                .thenReturn(Optional.of(toUpdate));
//        when(restaurantMapper.mapToUpdateEntity(dto, toUpdate))
//                .thenReturn(toSave);
//        when(restaurantRepository.save(toSave))
//                .thenReturn(afterUpdate);
//        when(restaurantMapper.mapToAdminDTO(afterUpdate))
//                .thenReturn(expectedResult);
//
//        RestaurantManageDTO result = underTest.update(dto);
//
//        assertEquals(expectedResult, result);
//    }
//
//    @Test
//    public void shouldNotUpdateRestaurantAndThrowExceptionWhenRestaurantNotFound() {
//        UUID restaurantId = UUID.randomUUID();
//        RestaurantManageDTO dto = new RestaurantDTO(null, "Pizza world", "This is pizza world");
//
//        when(tokenService.getRestaurantId())
//                .thenReturn(restaurantId);
//        when(restaurantRepository.findById(restaurantId))
//                .thenReturn(Optional.empty());
//
//        assertThrows(RestaurantNotFoundException.class, () -> underTest.update(dto));
//
//        verify(restaurantRepository, never()).save(any());
//        verify(restaurantMapper, never()).mapToUpdateEntity(any(), any());
//        verify(restaurantMapper, never()).mapToAdminDTO(any());
//    }
//
//    private void mockContext(UUID restaurantId) {
//        UserDTO user = new UserDTO()
//        RequestContext.set(new RequestContext());
//    }
//
//    private Restaurant buildRestaurantEntity(String name, String description, ) {
//
//    }
//
//    private RestaurantCreateDTO buildRestaurantCreate() {
//        return new RestaurantCreateDTO("Pasta palace", "This is pasta palace", buildUserRequest());
//    }
//
//    private Restaurant
//
//    private Restaurant buildRestaurantFromDTO(RestaurantManageDTO dto) {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setName(dto.getName());
//        restaurant.setDescription(dto.getDescription());
//        return restaurant;
//    }
//
//    private Restaurant buildRestaurantFromCreate(RestaurantCreateDTO restaurantCreate) {
//        return buildRestaurant(null, null, restaurantCreate.name(), restaurantCreate.description());
//    }
//
//    private Restaurant cloneRestaurantWithId(Restaurant restaurant) {
//        return buildRestaurant(UUID.randomUUID(), null, restaurant.getName(), restaurant.getDescription());
//    }
//
//    private Restaurant buildRestaurant(UUID id, UUID ownerId, String name, String description) {
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(id);
//        restaurant.setOwnerId(ownerId);
//        restaurant.setName(name);
//        restaurant.setDescription(description);
//        restaurant.setDefaultLanguage();
//        return restaurant;
//    }
//
//    private RestaurantClientDTO buildRestaurantClientDTO(Restaurant restaurant) {
//        RestaurantClientDTO dto = new RestaurantDTO<>();
//        dto.setId(restaurant.getId());
//        dto.setName(restaurant.getName());
//        dto.setDescription(restaurant.getDescription().get());
//        dto.setDefaultLanguage(restaurant.getDefaultLanguage());
//        return dto;
//    }
//
//    private RestaurantManageDTO buildRestaurantAdminDTO(Restaurant restaurant) {
//        RestaurantManageDTO dto = new RestaurantDTO<>();
//        dto.setId(restaurant.getId());
//        dto.setName(restaurant.getName());
//        dto.setDescription(restaurant.getDescription());
//        dto.setDefaultLanguage(restaurant.getDefaultLanguage());
//        return dto;
//    }
//
//    private UserRequestDTO buildUserRequest() {
//        return new UserRequestDTO(
//                "owner@example.com",
//                "restaurantOwner",
//                "Jane",
//                "Doe",
//                new HashMap<>()
//        );
//    }
}