package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.TranslatedStringHelper;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.exception.IngredientsNotFoundException;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {
    @InjectMocks
    private IngredientService underTest;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

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
    void shouldReturnEmptyListWhenIdsNull() {
        // when
        List<Ingredient> result = underTest.getEntitiesByIds(null, RESTAURANT_ID);

        // then
        assertThat(result, empty());

        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), eq(RESTAURANT_ID));
    }

    @Test
    void shouldReturnEmptyListWhenIdsEmpty() {
        // when
        List<Ingredient> result = underTest.getEntitiesByIds(List.of(), RESTAURANT_ID);

        // then
        assertThat(result, empty());

        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), eq(RESTAURANT_ID));
    }

    @Test
    void shouldReturnAllFoundIngredientsWhenAllExist() {
        // given
        UUID firstId = UUID.randomUUID();
        UUID secondId = UUID.randomUUID();
        List<UUID> ids = List.of(firstId, secondId);

        Ingredient firstIngredient = buildIngredientEntity(firstId, RESTAURANT_ID, "onion");
        Ingredient secondIngredient = buildIngredientEntity(secondId, RESTAURANT_ID, "garlic");
        when(ingredientRepository.findByIdInAndRestaurantId(ids, RESTAURANT_ID))
                .thenReturn(List.of(firstIngredient, secondIngredient));

        // when
        List<Ingredient> result = underTest.getEntitiesByIds(ids, RESTAURANT_ID);

        // then
        verify(ingredientRepository).findByIdInAndRestaurantId(ids, RESTAURANT_ID);

        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstIngredient, secondIngredient));
    }

    @Test
    void shouldThrowExceptionWhenSomeIngredientsMissing() {
        // given
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<UUID> ids = List.of(id1, id2);

        Ingredient ingredient1 = buildIngredientEntity(id1, RESTAURANT_ID, "onion");
        when(ingredientRepository.findByIdInAndRestaurantId(ids, RESTAURANT_ID)).thenReturn(List.of(ingredient1));

        // when
        assertThrows(IngredientsNotFoundException.class, () -> underTest.getEntitiesByIds(ids, RESTAURANT_ID));
    }

    @Test
    void shouldThrowExceptionWhenAllIngredientsAreMissing() {
        // given
        List<UUID> ids = List.of(UUID.randomUUID(), UUID.randomUUID());
        when(ingredientRepository.findByIdInAndRestaurantId(ids, RESTAURANT_ID)).thenReturn(List.of());

        // when + then
        assertThrows(IngredientsNotFoundException.class, () -> underTest.getEntitiesByIds(ids, RESTAURANT_ID));
    }

    @Test
    void shouldThrowIngredientNotFoundExceptionWhenIngredientDoesNotExist() {
        // given
        UUID id = UUID.randomUUID();
        when(ingredientRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(IngredientNotFoundException.class, () -> underTest.getManageDTO(id));

        // then
        verify(ingredientMapper, never()).mapToManageDTO(any());
    }

    private Ingredient buildIngredientEntity(UUID id, UUID restaurantId, String name) {
        Ingredient dto = new Ingredient();
        dto.setId(id);
        dto.setRestaurantId(restaurantId);
        dto.setName(TranslatedStringHelper.buildEntity(name, LANGUAGE));
        return dto;
    }
}