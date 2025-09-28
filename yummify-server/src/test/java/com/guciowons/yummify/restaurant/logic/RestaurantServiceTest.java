package com.guciowons.yummify.restaurant.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.restaurant.data.RestaurantRepository;
import com.guciowons.yummify.restaurant.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import com.guciowons.yummify.restaurant.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.mapper.RestaurantMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
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
    private PublicUserCreateService userCreateService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    private final Language LANGUAGE = Language.EN;
    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final String RESTAURANT_NAME = "Pasta palace";
    private final String RESTAURANT_DESCRIPTION = "This is pasta palace";

    @BeforeEach
    void setUp() {
        UserDTO mockUser = new UserDTO();
        mockUser.setId(UUID.randomUUID());
        mockUser.setRestaurantId(RESTAURANT_ID);
        mockUser.setUsername("testUser");

        RequestContext.set(new RequestContext(mockUser, LANGUAGE, LANGUAGE));
    }

    @AfterEach
    void tearDown() {
        RequestContext.clear();
    }

    @Test
    void shouldCreateRestaurant() {
        // given
        UUID ownerId = UUID.randomUUID();

        RestaurantCreateDTO restaurantCreate = buildCreateDTO(RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        Restaurant restaurant = buildEntity(null, null, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        Restaurant savedRestaurant = buildEntity(RESTAURANT_ID, null, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        RestaurantManageDTO expectedResult = buildManageDTO(RESTAURANT_ID, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);

        when(restaurantMapper.mapToSaveEntity(restaurantCreate.restaurant())).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(savedRestaurant);
        when(userCreateService.createUserWithPassword(restaurantCreate.owner())).thenReturn(ownerId);
        when(restaurantMapper.mapToManageDTO(savedRestaurant)).thenReturn(expectedResult);

        // when
        RestaurantManageDTO result = underTest.create(restaurantCreate);

        // then
        ArgumentCaptor<UserRequestDTO> userCaptor = ArgumentCaptor.forClass(UserRequestDTO.class);
        verify(userCreateService).createUserWithPassword(userCaptor.capture());
        UserRequestDTO capturedUser = userCaptor.getValue();
        assertEquals(RESTAURANT_ID.toString(), capturedUser.getAttributes().get("restaurantId").getFirst());

        verify(restaurantRepository).save(restaurant);

        assertEquals(ownerId, savedRestaurant.getOwnerId());
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldGetRestaurantForClient() {
        // given
        Restaurant restaurant = buildEntity(RESTAURANT_ID, UUID.randomUUID(), RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        RestaurantClientDTO expectedResult = buildClientDTO(RESTAURANT_ID, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);

        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.mapToClientDTO(restaurant)).thenReturn(expectedResult);

        // when
        RestaurantClientDTO result = underTest.getForClient();

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotGetRestaurantForClientAndThrowExceptionWhenRestaurantNotFound() {
        // given
        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(RestaurantNotFoundException.class, () -> underTest.getForClient());

        // then
        verify(restaurantMapper, never()).mapToClientDTO(any());
    }

    @Test
    public void shouldGetRestaurantForAdmin() {
        // given
        Restaurant restaurant = buildEntity(RESTAURANT_ID, UUID.randomUUID(), RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        RestaurantManageDTO expectedResult = buildManageDTO(RESTAURANT_ID, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);

        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.mapToManageDTO(restaurant)).thenReturn(expectedResult);

        // when
        RestaurantManageDTO result = underTest.getForAdmin();

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotGetRestaurantForAdminAndThrowExceptionWhenRestaurantNotFound() {
        // given
        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(RestaurantNotFoundException.class, () -> underTest.getForAdmin());

        // then
        verify(restaurantMapper, never()).mapToManageDTO(any());
    }

    @Test
    public void shouldUpdateRestaurant() {
        // given
        UUID ownerId = UUID.randomUUID();
        String newName = "Pizza world";
        String newDescription = "This is pizza world";
        Restaurant toUpdate = buildEntity(RESTAURANT_ID, ownerId, RESTAURANT_NAME, RESTAURANT_DESCRIPTION);
        RestaurantManageDTO dto = buildManageDTO(RESTAURANT_ID, newName, newDescription);
        Restaurant updated = buildEntity(RESTAURANT_ID, ownerId, newName, newDescription);
        RestaurantManageDTO expectedResult = buildManageDTO(RESTAURANT_ID, newName, newDescription);

        when(restaurantRepository.findById(toUpdate.getId())).thenReturn(Optional.of(toUpdate));
        when(restaurantMapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(restaurantMapper.mapToManageDTO(updated)).thenReturn(expectedResult);

        // when
        RestaurantManageDTO result = underTest.update(dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateRestaurantAndThrowExceptionWhenRestaurantNotFound() {
        String newName = "Pizza world";
        String newDescription = "This is pizza world";
        RestaurantManageDTO dto = buildManageDTO(RESTAURANT_ID, newName, newDescription);

        when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> underTest.update(dto));

        verify(restaurantRepository, never()).save(any());
        verify(restaurantMapper, never()).mapToUpdateEntity(any(), any());
        verify(restaurantMapper, never()).mapToManageDTO(any());
    }

    private RestaurantCreateDTO buildCreateDTO(String name, String description) {
        return new RestaurantCreateDTO(buildManageDTO(null, name, description), buildUserRequest());
    }

    private RestaurantManageDTO buildManageDTO(UUID id, String name, String description) {
        RestaurantManageDTO manageDTO = new RestaurantManageDTO();
        manageDTO.setId(id);
        manageDTO.setName(name);
        manageDTO.setDescription(buildTranslatedStringDTO(description));
        return manageDTO;
    }

    private TranslatedStringDTO buildTranslatedStringDTO(String text) {
        return new TranslatedStringDTO(Map.of(LANGUAGE, text));
    }

    private Restaurant buildEntity(UUID id, UUID ownerId, String name, String description) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setOwnerId(ownerId);
        restaurant.setName(name);
        restaurant.setDescription(buildTranslatedString(description));
        restaurant.setDefaultLanguage(LANGUAGE);
        return restaurant;
    }

    private TranslatedString buildTranslatedString(String text) {
        TranslatedString translatedString = new TranslatedString();
        translatedString.put(LANGUAGE, text);
        return translatedString;
    }

    private RestaurantClientDTO buildClientDTO(UUID id, String name, String description) {
        RestaurantClientDTO clientDTO = new RestaurantClientDTO();
        clientDTO.setId(id);
        clientDTO.setName(name);
        clientDTO.setDescription(description);
        return clientDTO;
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