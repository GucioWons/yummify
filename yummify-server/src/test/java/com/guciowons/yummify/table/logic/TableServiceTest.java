package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.infrastructure.repository.TableRepository;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.exception.TableExistsByNameException;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import com.guciowons.yummify.table.application.mapper.TableMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

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
        TableDTO dto = buildDTO(null);
        Table table = buildTable(null, null);
        Table savedTable = buildTable(TABLE_ID, RESTAURANT_ID);
        TableDTO expectedResult = buildDTO(TABLE_ID);

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
        TableDTO dto = buildDTO(null);
        when(tableRepository.existsByNameAndRestaurantId(TABLE_NAME, RESTAURANT_ID)).thenReturn(true);

        // when
        assertThrows(TableExistsByNameException.class, () -> underTest.create(dto));

        // then
        verify(tableRepository, never()).save(any());
        verify(userCreateService, never()).createUser(any());
    }

    @Test
    void shouldThrowTableNotFoundExceptionWhenTableDoesNotExist() {
        // given
        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(TableNotFoundException.class, () -> underTest.getById(TABLE_ID));

        // then
        verify(tableMapper, never()).mapToDTO(any());
    }

    private TableDTO buildDTO(UUID id) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(id);
        tableDTO.setName("A01");
        return tableDTO;
    }

    private Table buildTable(UUID id, UUID restaurantId) {
        Table table = new Table();
        table.setId(id);
        table.setRestaurantId(restaurantId);
        table.setUserId(null);
        table.setName("A01");
        return table;
    }
}