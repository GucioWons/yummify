package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.IngredientClientDTO;
import com.guciowons.yummify.dish.IngredientManageDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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

    private final UUID INGREDIENT_ID = UUID.randomUUID();
    private final String INGREDIENT_NAME = "Onion";

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
    void shouldCreateIngredient() {
        // given
        IngredientManageDTO dto = buildManageDTO(null, INGREDIENT_NAME);
        Ingredient ingredient = buildEntity(null, null, INGREDIENT_NAME);
        Ingredient savedIngredient = buildEntity(INGREDIENT_ID, RESTAURANT_ID, INGREDIENT_NAME);
        IngredientManageDTO expectedResult = buildManageDTO(INGREDIENT_ID, INGREDIENT_NAME);

        when(ingredientMapper.mapToSaveEntity(dto)).thenReturn(ingredient);
        when(ingredientRepository.save(ingredient)).thenReturn(savedIngredient);
        when(ingredientMapper.mapToManageDTO(savedIngredient)).thenReturn(expectedResult);

        // when
        IngredientManageDTO result = underTest.create(dto);

        // then
        ArgumentCaptor<Ingredient> captor = ArgumentCaptor.forClass(Ingredient.class);
        verify(ingredientRepository).save(captor.capture());
        Ingredient capturedIngredient = captor.getValue();
        assertEquals(RESTAURANT_ID, capturedIngredient.getRestaurantId());

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnAllIngredients() {
        // given
        UUID secondIngredientId = UUID.randomUUID();
        String secondIngredientName = "Garlic";

        Ingredient firstIngredient = buildEntity(INGREDIENT_ID, RESTAURANT_ID, INGREDIENT_NAME);
        IngredientClientDTO firstIngredientDTO = buildClientDTO(INGREDIENT_ID, INGREDIENT_NAME);
        Ingredient secondIngredient = buildEntity(secondIngredientId, RESTAURANT_ID, secondIngredientName);
        IngredientClientDTO secondIngredientDTO = buildClientDTO(secondIngredientId, secondIngredientName);

        when(ingredientRepository.findAllByRestaurantId(RESTAURANT_ID))
                .thenReturn(List.of(firstIngredient, secondIngredient));
        when(ingredientMapper.mapToClientDTO(firstIngredient)).thenReturn(firstIngredientDTO);
        when(ingredientMapper.mapToClientDTO(secondIngredient)).thenReturn(secondIngredientDTO);

        // when
        List<IngredientClientDTO> result = underTest.getAll();

        // then
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstIngredientDTO, secondIngredientDTO));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoIngredients() {
        // given
        when(ingredientRepository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        // when
        List<IngredientClientDTO> result = underTest.getAll();

        // then
        verify(ingredientMapper, never()).mapToClientDTO(any());
        assertThat(result, empty());
    }

    @Test
    void shouldGetIngredient() {
        // given
        Ingredient ingredient = buildEntity(INGREDIENT_ID, RESTAURANT_ID, INGREDIENT_NAME);
        IngredientManageDTO expectedResult = buildManageDTO(INGREDIENT_ID, INGREDIENT_NAME);

        when(ingredientRepository.findByIdAndRestaurantId(INGREDIENT_ID, RESTAURANT_ID))
                .thenReturn(Optional.of(ingredient));
        when(ingredientMapper.mapToManageDTO(ingredient)).thenReturn(expectedResult);

        // when
        IngredientManageDTO result = underTest.getById(INGREDIENT_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetIngredientAndThrowExceptionWhenIngredientNotFound() {
        // given
        when(ingredientRepository.findByIdAndRestaurantId(INGREDIENT_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(IngredientNotFoundException.class, () -> underTest.getById(INGREDIENT_ID));

        // then
        verify(ingredientMapper, never()).mapToManageDTO(any());
    }

    @Test
    public void shouldUpdateIngredient() {
        // given
        String newName = "Garlic";

        Ingredient toUpdate = buildEntity(INGREDIENT_ID, RESTAURANT_ID, INGREDIENT_NAME);
        IngredientManageDTO dto = buildManageDTO(INGREDIENT_ID, newName);
        Ingredient updated = buildEntity(INGREDIENT_ID, RESTAURANT_ID, newName);
        IngredientManageDTO expectedResult = buildManageDTO(INGREDIENT_ID, newName);

        when(ingredientRepository.findByIdAndRestaurantId(INGREDIENT_ID, RESTAURANT_ID))
                .thenReturn(Optional.of(toUpdate));
        when(ingredientMapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(ingredientMapper.mapToManageDTO(updated)).thenReturn(expectedResult);

        // when
        IngredientManageDTO result = underTest.update(INGREDIENT_ID, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateTableAndThrowExceptionWhenTableNotFound() {
        // given
        String newName = "Garlic";

        IngredientManageDTO dto = buildManageDTO(INGREDIENT_ID, newName);

        when(ingredientRepository.findByIdAndRestaurantId(INGREDIENT_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(IngredientNotFoundException.class, () -> underTest.update(INGREDIENT_ID, dto));

        // then
        verify(ingredientRepository, never()).save(any());
        verify(ingredientMapper, never()).mapToUpdateEntity(any(), any());
        verify(ingredientMapper, never()).mapToManageDTO(any());
    }

    @Test
    void getNotFoundException() {
    }

    private IngredientManageDTO buildManageDTO(UUID id, String name) {
        IngredientManageDTO dto = new IngredientManageDTO();
        dto.setId(id);
        dto.setName(buildTranslatedStringDTO(name));
        return dto;
    }

    private IngredientClientDTO buildClientDTO(UUID id, String name) {
        IngredientClientDTO dto = new IngredientClientDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    private Ingredient buildEntity(UUID id, UUID restaurantId, String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setRestaurantId(restaurantId);
        ingredient.setName(buildTranslatedString(name));
        return ingredient;
    }

    private TranslatedStringDTO buildTranslatedStringDTO(String text) {
        return new TranslatedStringDTO(Map.of(LANGUAGE, text));
    }

    private TranslatedString buildTranslatedString(String text) {
        TranslatedString translatedString = new TranslatedString();
        translatedString.put(LANGUAGE, text);
        return translatedString;
    }
}