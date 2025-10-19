package com.guciowons.yummify.common.core.service;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.TranslatedStringHelper;
import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.common.core.service.implementations.translatable.*;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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
class TranslatableRestaurantScopedServiceTest {
    @InjectMocks
    private TestTranslatableRestaurantScopedService underTest;

    @Mock
    private RestaurantScopedRepository<TestTranslatableRestaurantScopedEntity> repository;

    @Mock
    private TranslatableMapper<TestTranslatableRestaurantScopedEntity, TestTranslatableRestaurantScopedManageDTO, TestTranslatableRestaurantScopedClientDTO, TestTranslatableRestaurantScopedListDTO> mapper;

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
    void shouldCreateEntity() {
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
    void shouldReturnAllEntities() {
        // given
        UUID secondEntityId = UUID.randomUUID();
        String secondEntityTest = "second test";

        TestTranslatableRestaurantScopedEntity firstEntity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedListDTO firstDTO = buildListDTO(ENTITY_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedEntity secondEntity = buildEntity(secondEntityId, RESTAURANT_ID, secondEntityTest);
        TestTranslatableRestaurantScopedListDTO secondDTO = buildListDTO(secondEntityId, secondEntityTest);

        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(List.of(firstEntity, secondEntity));
        when(mapper.mapToListDTO(firstEntity)).thenReturn(firstDTO);
        when(mapper.mapToListDTO(secondEntity)).thenReturn(secondDTO);

        // when
        List<TestTranslatableRestaurantScopedListDTO> result = underTest.getAll();

        // then
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstDTO, secondDTO));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoEntities() {
        // given
        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        // when
        List<TestTranslatableRestaurantScopedListDTO> result = underTest.getAll();

        // then
        verify(mapper, never()).mapToClientDTO(any());
        assertThat(result, empty());
    }

    @Test
    void shouldGetManageDTO() {
        // given
        TestTranslatableRestaurantScopedEntity entity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedManageDTO expectedResult = buildManageDTO(ENTITY_ID, ENTITY_TEST);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(entity));
        when(mapper.mapToManageDTO(entity)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedManageDTO result = underTest.getManageDTO(ENTITY_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetManageDTOAndThrowExceptionWhenEntityNotFound() {
        // given
        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.getManageDTO(ENTITY_ID));

        // then
        verify(mapper, never()).mapToManageDTO(any());
    }

    @Test
    void shouldGetClientDTO() {
        // given
        TestTranslatableRestaurantScopedEntity entity = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedClientDTO expectedResult = buildClientDTO(ENTITY_ID, ENTITY_TEST);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(entity));
        when(mapper.mapToClientDTO(entity)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedClientDTO result = underTest.getClientDTO(ENTITY_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetClientDTOAndThrowExceptionWhenEntityNotFound() {
        // given
        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.getClientDTO(ENTITY_ID));

        // then
        verify(mapper, never()).mapToClientDTO(any());
    }

    @Test
    public void shouldUpdateEntity() {
        // given
        String newTest = "updated test";

        TestTranslatableRestaurantScopedEntity toUpdate = buildEntity(ENTITY_ID, RESTAURANT_ID, ENTITY_TEST);
        TestTranslatableRestaurantScopedManageDTO dto = buildManageDTO(ENTITY_ID, newTest);
        TestTranslatableRestaurantScopedEntity updated = buildEntity(ENTITY_ID, RESTAURANT_ID, newTest);
        TestTranslatableRestaurantScopedEntity saved = buildEntity(ENTITY_ID, RESTAURANT_ID, newTest);
        TestTranslatableRestaurantScopedManageDTO expectedResult = buildManageDTO(ENTITY_ID, newTest);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(toUpdate));
        when(mapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(saved);
        when(mapper.mapToManageDTO(saved)).thenReturn(expectedResult);

        // when
        TestTranslatableRestaurantScopedManageDTO result = underTest.update(ENTITY_ID, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateEntityAndThrowExceptionWhenEntityNotFound() {
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
        dto.setTest(TranslatedStringHelper.buildDTO(name, LANGUAGE));
        return dto;
    }

    private TestTranslatableRestaurantScopedClientDTO buildClientDTO(UUID id, String name) {
        TestTranslatableRestaurantScopedClientDTO dto = new TestTranslatableRestaurantScopedClientDTO();
        dto.setId(id);
        dto.setTest(name);
        return dto;
    }

    private TestTranslatableRestaurantScopedListDTO buildListDTO(UUID id, String name) {
        TestTranslatableRestaurantScopedListDTO dto = new TestTranslatableRestaurantScopedListDTO();
        dto.setId(id);
        dto.setTest(name);
        return dto;
    }

    private TestTranslatableRestaurantScopedEntity buildEntity(UUID id, UUID restaurantId, String name) {
        TestTranslatableRestaurantScopedEntity ingredient = new TestTranslatableRestaurantScopedEntity();
        ingredient.setId(id);
        ingredient.setRestaurantId(restaurantId);
        ingredient.setTest(TranslatedStringHelper.buildEntity(name, LANGUAGE));
        return ingredient;
    }
}