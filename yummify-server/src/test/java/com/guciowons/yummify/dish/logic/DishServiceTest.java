package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.TranslatedStringHelper;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {
    @InjectMocks
    private DishService underTest;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

    private final UUID DISH_ID = UUID.randomUUID();

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
    void shouldSetNewIngredientsListInAfterMappingEntity() {
        // given
        UUID firstIngredientId = UUID.randomUUID();
        String firstIngredientName = "onion";
        UUID secondIngredientId = UUID.randomUUID();
        String secondIngredientName = "garlic";
        List<UUID> ingredientIds = List.of(firstIngredientId, secondIngredientId);

        UUID dishId = UUID.randomUUID();
        String dishName = "Onion soup";

        IngredientClientDTO firstIngredientDTO = buildIngredientClientDTO(firstIngredientId, firstIngredientName);
        IngredientClientDTO secondIngredientDTO = buildIngredientClientDTO(secondIngredientId, secondIngredientName);
        DishManageDTO dto = buildManageDTO(dishId, dishName, firstIngredientDTO, secondIngredientDTO);

        Ingredient firstIngredient = buildIngredientEntity(firstIngredientId, RESTAURANT_ID, firstIngredientName);
        Ingredient secondIngredient = buildIngredientEntity(secondIngredientId, RESTAURANT_ID, secondIngredientName);
        Dish entity = buildEntity(dishId, RESTAURANT_ID, dishName, null);

        when(ingredientService.getEntitiesByIds(ingredientIds, RESTAURANT_ID))
                .thenReturn(List.of(firstIngredient, secondIngredient));

        // when
        ReflectionTestUtils.invokeMethod(underTest, "afterMappingEntity", dto, entity);

        // then
        verify(ingredientService).getEntitiesByIds(ingredientIds, RESTAURANT_ID);

        assertThat(entity.getIngredients(), containsInAnyOrder(firstIngredient, secondIngredient));
    }

    @Test
    void shouldClearAndSetIngredientsListInAfterMappingEntity() {
        // given
        UUID firstIngredientId = UUID.randomUUID();
        String firstIngredientName = "onion";
        UUID secondIngredientId = UUID.randomUUID();
        String secondIngredientName = "garlic";
        List<UUID> ingredientIds = List.of(firstIngredientId, secondIngredientId);

        UUID dishId = UUID.randomUUID();
        String dishName = "Onion soup";

        IngredientClientDTO firstIngredientDTO = buildIngredientClientDTO(firstIngredientId, firstIngredientName);
        IngredientClientDTO secondIngredientDTO = buildIngredientClientDTO(secondIngredientId, secondIngredientName);
        DishManageDTO dto = buildManageDTO(dishId, dishName, firstIngredientDTO, secondIngredientDTO);

        Ingredient previousIngredient = buildIngredientEntity(UUID.randomUUID(), RESTAURANT_ID, "apple");
        Ingredient firstIngredient = buildIngredientEntity(firstIngredientId, RESTAURANT_ID, firstIngredientName);
        Ingredient secondIngredient = buildIngredientEntity(secondIngredientId, RESTAURANT_ID, secondIngredientName);
        Dish entity = buildEntity(dishId, RESTAURANT_ID, dishName, new ArrayList<>(List.of(previousIngredient)));

        when(ingredientService.getEntitiesByIds(ingredientIds, RESTAURANT_ID))
                .thenReturn(List.of(firstIngredient, secondIngredient));

        // when
        ReflectionTestUtils.invokeMethod(underTest, "afterMappingEntity", dto, entity);

        // then
        verify(ingredientService).getEntitiesByIds(ingredientIds, RESTAURANT_ID);

        assertThat(entity.getIngredients(), containsInAnyOrder(firstIngredient, secondIngredient));
        assertThat(entity.getIngredients(), not(Matchers.contains(previousIngredient)));
    }

    @Test
    void shouldValidateDishId() {
        // given
        Dish entity = new Dish();
        when(dishRepository.findByIdAndRestaurantId(DISH_ID, RESTAURANT_ID)).thenReturn(Optional.of(entity));

        // when + then
        underTest.validateDishId(DISH_ID);
    }

    @Test
    void shouldThrowWhenValidateDishIdAndDishNotFound() {
        // given
        when(dishRepository.findByIdAndRestaurantId(DISH_ID, RESTAURANT_ID))
                .thenReturn(Optional.empty());

        // when + then
        assertThrows(DishNotFoundException.class, () -> underTest.validateDishId(DISH_ID));
    }

    @Test
    void shouldNotGetDishAndThrowExceptionWhenDishNotFound() {
        // given
        when(dishRepository.findByIdAndRestaurantId(DISH_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(DishNotFoundException.class, () -> underTest.getManageDTO(DISH_ID));

        // then
        verify(dishMapper, never()).mapToManageDTO(any());
    }

    private DishManageDTO buildManageDTO(UUID id, String name, IngredientClientDTO... ingredients) {
        DishManageDTO dto = new DishManageDTO();
        dto.setId(id);
        dto.setName(TranslatedStringHelper.buildDTO(name, LANGUAGE));
        dto.setIngredients(List.of(ingredients));
        return dto;
    }

    private Dish buildEntity(UUID id, UUID restaurantId, String name, List<Ingredient> ingredients) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setRestaurantId(restaurantId);
        dish.setName(TranslatedStringHelper.buildEntity(name, LANGUAGE));
        dish.setIngredients(ingredients);
        return dish;
    }

    private IngredientClientDTO buildIngredientClientDTO(UUID id, String name) {
        IngredientClientDTO dto = new IngredientClientDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    private Ingredient buildIngredientEntity(UUID id, UUID restaurantId, String name) {
        Ingredient dto = new Ingredient();
        dto.setId(id);
        dto.setRestaurantId(restaurantId);
        dto.setName(TranslatedStringHelper.buildEntity(name, LANGUAGE));
        return dto;
    }
}