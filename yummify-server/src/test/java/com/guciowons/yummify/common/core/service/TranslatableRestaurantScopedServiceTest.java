package com.guciowons.yummify.common.core.service;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.core.service.implementations.translatable.*;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
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
class TranslatableRestaurantScopedServiceTest {
    @InjectMocks
    private TestTranslatableRestaurantScopedService underTest;

    @Mock
    private TestTranslatableRestaurantScopedRepository repository;

    @Mock
    private TestTranslatableRestaurantScopedMapper mapper;

    private final Language LANGUAGE = Language.EN;
    private final UUID RESTAURANT_ID = UUID.randomUUID();

    private final UUID ENTITY_ID = UUID.randomUUID();
    private final String ENTITY_TEST = "test";

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
        TestTranslatableRestaurantScopedManageDTO dto = buildManageDTO(null, ENTITY_TEST);
        TestTranslatableRestaurantScopedEntity entity = buildEntity(null, null, ENTITY_TEST);
        TestTranslatableRestaurantScopedEntity savedEntity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedManageDTO expectedResult = buildManageDTO(ENTITY_ID, ENTITY_TEST);

        when(mapper.mapToSaveEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.mapToManageDTO(savedEntity)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedManageDTO result = underTest.create(dto);

        // then
        ArgumentCaptor<TestTranslatableRestaurantScopedEntity> captor = ArgumentCaptor.forClass(TestTranslatableRestaurantScopedEntity.class);
        verify(repository).save(captor.capture());
        TestTranslatableRestaurantScopedEntity capturedEntity = captor.getValue();
        assertEquals(RESTAURANT_ID, capturedEntity.getRestaurantId());

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnAllIngredients() {
        // given
        UUID secondEntityId = UUID.randomUUID();
        String secondEntityTest = "second test";

        TestTranslatableRestaurantScopedEntity firstEntity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedClientDTO firstDTO = buildClientDTO(ENTITY_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedEntity secondEntity = buildEntity(secondEntityId, RESTAURANT_ID, secondEntityTest);
        TestTranslatableRestaurantScopedClientDTO secondDTO = buildClientDTO(secondEntityId, secondEntityTest);

        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(List.of(firstEntity, secondEntity));
        when(mapper.mapToClientDTO(firstEntity)).thenReturn(firstDTO);
        when(mapper.mapToClientDTO(secondEntity)).thenReturn(secondDTO);

        // when
        List<TestTranslatableRestaurantScopedClientDTO> result = underTest.getAll();

        // then
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstDTO, secondDTO));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoIngredients() {
        // given
        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        // when
        List<TestTranslatableRestaurantScopedClientDTO> result = underTest.getAll();

        // then
        verify(mapper, never()).mapToClientDTO(any());
        assertThat(result, empty());
    }

    @Test
    void shouldGetIngredient() {
        // given
        TestTranslatableRestaurantScopedEntity entity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedManageDTO expectedResult = buildManageDTO(ENTITY_ID, ENTITY_TEST);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(entity));
        when(mapper.mapToManageDTO(entity)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedManageDTO result = underTest.getById(ENTITY_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetIngredientAndThrowExceptionWhenIngredientNotFound() {
        // given
        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.getById(ENTITY_ID));

        // then
        verify(mapper, never()).mapToManageDTO(any());
    }

    @Test
    public void shouldUpdateIngredient() {
        // given
        String newTest = "updated test";

        TestTranslatableRestaurantScopedEntity toUpdate = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedManageDTO dto = buildManageDTO(ENTITY_ID, newTest);
        TestTranslatableRestaurantScopedEntity updated = buildEntity(ENTITY_ID, RESTAURANT_ID, newTest);
        TestTranslatableRestaurantScopedManageDTO expectedResult = buildManageDTO(ENTITY_ID, newTest);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(toUpdate));
        when(mapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(mapper.mapToManageDTO(updated)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedManageDTO result = underTest.update(ENTITY_ID, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateTableAndThrowExceptionWhenTableNotFound() {
        // given
        String newTest = "updated test";

        TestTranslatableRestaurantScopedManageDTO dto = buildManageDTO(ENTITY_ID, newTest);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.update(ENTITY_ID, dto));

        // then
        verify(repository, never()).save(any());
        verify(mapper, never()).mapToUpdateEntity(any(), any());
        verify(mapper, never()).mapToManageDTO(any());
    }

    private TestTranslatableRestaurantScopedManageDTO buildManageDTO(UUID id, String name) {
        TestTranslatableRestaurantScopedManageDTO dto = new TestTranslatableRestaurantScopedManageDTO();
        dto.setId(id);
        dto.setTest(buildTranslatedStringDTO(name));
        return dto;
    }

    private TestTranslatableRestaurantScopedClientDTO buildClientDTO(UUID id, String name) {
        TestTranslatableRestaurantScopedClientDTO dto = new TestTranslatableRestaurantScopedClientDTO();
        dto.setId(id);
        dto.setTest(name);
        return dto;
    }

    private TestTranslatableRestaurantScopedEntity buildEntity(UUID id, UUID restaurantId, String name) {
        TestTranslatableRestaurantScopedEntity ingredient = new TestTranslatableRestaurantScopedEntity();
        ingredient.setId(id);
        ingredient.setRestaurantId(restaurantId);
        ingredient.setTest(buildTranslatedString(name));
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