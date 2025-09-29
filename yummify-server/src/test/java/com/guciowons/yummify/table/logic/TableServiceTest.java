package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.data.TableRepository;
import com.guciowons.yummify.table.entity.Table;
import com.guciowons.yummify.table.exception.TableExistsByNameException;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import com.guciowons.yummify.table.mapper.TableMapper;
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
class TableServiceTest {
    @InjectMocks
    private TableService underTest;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private TableMapper tableMapper;

    @Mock
    private PublicUserCreateService userCreateService;

    private final Language LANGUAGE = Language.EN;
    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final UUID TABLE_ID = UUID.randomUUID();
    private final UUID TABLE_USER_ID = UUID.randomUUID();
    private final String TABLE_NAME = "A01";

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
    void shouldCreateTable() {
        // given
        TableDTO dto = buildDTO(null, TABLE_NAME);
        Table table = buildTable(null, null, null, TABLE_NAME);
        Table savedTable = buildTable(TABLE_ID, RESTAURANT_ID, null, TABLE_NAME);
        TableDTO expectedResult = buildDTO(TABLE_ID, TABLE_NAME);

        when(tableRepository.existsByNameAndRestaurantId(TABLE_NAME, RESTAURANT_ID)).thenReturn(false);
        when(tableMapper.mapToEntity(dto)).thenReturn(table);
        when(tableRepository.save(table)).thenReturn(savedTable);
        when(userCreateService.createUser(any())).thenReturn(TABLE_USER_ID);
        when(tableMapper.mapToDTO(savedTable)).thenReturn(expectedResult);

        // when
        TableDTO result = underTest.create(dto);

        // then
        ArgumentCaptor<UserRequestDTO> userCaptor = ArgumentCaptor.forClass(UserRequestDTO.class);
        verify(userCreateService).createUser(userCaptor.capture());
        UserRequestDTO capturedUser = userCaptor.getValue();
        assertEquals(TABLE_ID + "@table.fake", capturedUser.getEmail());
        assertEquals(RESTAURANT_ID.toString(), capturedUser.getAttributes().get("restaurantId").getFirst());

        verify(tableRepository).save(table);

        assertEquals(RESTAURANT_ID, table.getRestaurantId());
        assertEquals(TABLE_USER_ID, savedTable.getUserId());
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotCreateTableAndThrowExceptionWhenTableExistsByName() {
        // given
        TableDTO dto = buildDTO(null, TABLE_NAME);
        when(tableRepository.existsByNameAndRestaurantId(TABLE_NAME, RESTAURANT_ID)).thenReturn(true);

        // when
        assertThrows(TableExistsByNameException.class, () -> underTest.create(dto));

        // then
        verify(tableRepository, never()).save(any());
        verify(userCreateService, never()).createUser(any());
    }

    @Test
    void shouldReturnAllTables() {
        // given
        UUID secondTableId = UUID.randomUUID();
        String secondTableName = "A02";

        Table firstTable = buildTable(TABLE_ID, RESTAURANT_ID, TABLE_USER_ID, TABLE_NAME);
        TableDTO firstTableDTO = buildDTO(TABLE_ID, TABLE_NAME);
        Table secondTable = buildTable(secondTableId, RESTAURANT_ID, UUID.randomUUID(), secondTableName);
        TableDTO secondTableDTO = buildDTO(secondTableId, secondTableName);

        when(tableRepository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(List.of(firstTable, secondTable));
        when(tableMapper.mapToDTO(firstTable)).thenReturn(firstTableDTO);
        when(tableMapper.mapToDTO(secondTable)).thenReturn(secondTableDTO);

        // when
        List<TableDTO> result = underTest.getAll();

        // then
        assertEquals(2, result.size());
        assertThat(result, containsInAnyOrder(firstTableDTO, secondTableDTO));
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoTables() {
        // given
        when(tableRepository.findAllByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        // when
        List<TableDTO> result = underTest.getAll();

        // then
        verify(tableMapper, never()).mapToDTO(any());
        assertThat(result, empty());
    }

    @Test
    void shouldGetTable() {
        // given
        Table table = buildTable(TABLE_ID, RESTAURANT_ID, TABLE_USER_ID, TABLE_NAME);
        TableDTO expectedResult = buildDTO(TABLE_ID, TABLE_NAME);

        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.of(table));
        when(tableMapper.mapToDTO(table)).thenReturn(expectedResult);

        // when
        TableDTO result = underTest.getById(TABLE_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldNotGetTableAndThrowExceptionWhenTableNotFound() {
        // given
        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(TableNotFoundException.class, () -> underTest.getById(TABLE_ID));

        // then
        verify(tableMapper, never()).mapToDTO(any());
    }

    @Test
    public void shouldUpdateTable() {
        // given
        String newName = "A02";

        Table toUpdate = buildTable(TABLE_ID, RESTAURANT_ID, TABLE_USER_ID, TABLE_NAME);
        TableDTO dto = buildDTO(TABLE_ID, newName);
        Table updated = buildTable(TABLE_ID, RESTAURANT_ID, TABLE_USER_ID, newName);
        TableDTO expectedResult = buildDTO(TABLE_ID, newName);

        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.of(toUpdate));
        when(tableMapper.mapToUpdateEntity(dto, toUpdate)).thenReturn(updated);
        when(tableMapper.mapToDTO(updated)).thenReturn(expectedResult);

        // when
        TableDTO result = underTest.update(TABLE_ID, dto);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotUpdateTableAndThrowExceptionWhenTableNotFound() {
        // given
        String newName = "A02";

        TableDTO dto = buildDTO(TABLE_ID, newName);

        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(TableNotFoundException.class, () -> underTest.update(TABLE_ID, dto));

        // then
        verify(tableRepository, never()).save(any());
        verify(tableMapper, never()).mapToUpdateEntity(any(), any());
        verify(tableMapper, never()).mapToDTO(any());
    }

    private TableDTO buildDTO(UUID id, String name) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(id);
        tableDTO.setName(name);
        return tableDTO;
    }

    private Table buildTable(UUID id, UUID restaurantId, UUID userId, String name) {
        Table table = new Table();
        table.setId(id);
        table.setRestaurantId(restaurantId);
        table.setUserId(userId);
        table.setName(name);
        return table;
    }
}