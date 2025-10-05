package com.guciowons.yummify.common.core.service;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.core.service.implementations.restaurantscoped.*;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class RestaurantScopedServiceTest {
    @InjectMocks
    private TestRestaurantScopedService underTest;

    @Mock
    private TestRestaurantScopedRepository repository;

    @Mock
    private TestRestaurantScopedMapper mapper;

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
    void shouldReturnAllEntities() {
        // given
        UUID secondEntityId = UUID.randomUUID();
        String secondEntityTest = "second test";

        TestRestaurantScopedEntity firstEntity = buildEntity(ENTITY_ID, ENTITY_TEST);
        TestRestaurantScopedDTO firstDTO = buildDTO(ENTITY_ID, ENTITY_TEST);
        TestRestaurantScopedEntity secondEntity = buildEntity(secondEntityId, secondEntityTest);
        TestRestaurantScopedDTO secondDTO = buildDTO(secondEntityId, secondEntityTest);

        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(List.of(firstEntity, secondEntity));
        when(mapper.mapToDTO(firstEntity)).thenReturn(firstDTO);
        when(mapper.mapToDTO(secondEntity)).thenReturn(secondDTO);

        // when
        List<TestRestaurantScopedDTO> result = underTest.getAll();

        // then
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstDTO, secondDTO));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoEntities() {
        // given
        when(repository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        // when
        List<TestRestaurantScopedDTO> result = underTest.getAll();

        // then
        verify(mapper, never()).mapToDTO(any());
        assertThat(result, empty());
    }

    @Test
    void shouldGetEntity() {
        // given
        TestRestaurantScopedEntity entity = buildEntity(ENTITY_ID, ENTITY_TEST);
        TestRestaurantScopedDTO expectedResult = buildDTO(ENTITY_ID, ENTITY_TEST);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(entity));
        when(mapper.mapToDTO(entity)).thenReturn(expectedResult);

        // when
        TestRestaurantScopedDTO result = underTest.getById(ENTITY_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetTableAndThrowExceptionWhenTableNotFound() {
        // given
        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.getById(ENTITY_ID));

        // then
        verify(mapper, never()).mapToDTO(any());
    }

    @Test
    public void shouldUpdateTable() {
        // given
        String newTest = "updated test";

        TestRestaurantScopedEntity toUpdate = buildEntity(ENTITY_ID, ENTITY_TEST);
        TestRestaurantScopedDTO dto = buildDTO(ENTITY_ID, newTest);
        TestRestaurantScopedEntity updated = buildEntity(ENTITY_ID, newTest);
        TestRestaurantScopedDTO expectedResult = buildDTO(ENTITY_ID, newTest);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.of(toUpdate));
        when(mapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(mapper.mapToDTO(updated)).thenReturn(expectedResult);

        // when
        TestRestaurantScopedDTO result = underTest.update(ENTITY_ID, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateTableAndThrowExceptionWhenTableNotFound() {
        // given
        String newTest = "updated test";

        TestRestaurantScopedDTO dto = buildDTO(ENTITY_ID, newTest);

        when(repository.findByIdAndRestaurantId(ENTITY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(SingleApiErrorException.class, () -> underTest.update(ENTITY_ID, dto));

        // then
        verify(repository, never()).save(any());
        verify(mapper, never()).mapToUpdateEntity(any(), any());
        verify(mapper, never()).mapToDTO(any());
    }

    private TestRestaurantScopedEntity buildEntity(UUID id, String test) {
        TestRestaurantScopedEntity entity = new TestRestaurantScopedEntity();
        entity.setId(id);
        entity.setRestaurantId(RESTAURANT_ID);
        entity.setTest(test);
        return entity;
    }

    private TestRestaurantScopedDTO buildDTO(UUID id, String test) {
        TestRestaurantScopedDTO dto = new TestRestaurantScopedDTO();
        dto.setId(id);
        dto.setTest(test);
        return dto;
    }
}