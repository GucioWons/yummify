package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.menu.data.MenuEntryRepository;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.entity.MenuEntry;
import com.guciowons.yummify.menu.entity.MenuSection;
import com.guciowons.yummify.menu.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.mapper.MenuEntryMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuEntryServiceTest {
    @InjectMocks
    private MenuEntryService underTest;

    @Mock
    private MenuEntryRepository menuEntryRepository;

    @Mock
    private MenuEntryMapper menuEntryMapper;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

    private final UUID ENTRY_ID = UUID.randomUUID();
    private final BigDecimal ENTRY_PRICE = BigDecimal.valueOf(20);
    private final Integer ENTRY_POSITION = 1;

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
    public void shouldCreateMenuEntry() {
        // given
        DishClientDTO dish = buildDishClientDTO(UUID.randomUUID());
        MenuEntryDTO dto = buildDto(null, dish, ENTRY_PRICE, ENTRY_POSITION);
        MenuEntry expectedResult = buildEntity(null, dish.id(), ENTRY_PRICE, ENTRY_POSITION);
        MenuSection section = new MenuSection();

        when(menuEntryMapper.mapToSaveEntity(dto)).thenReturn(expectedResult);
        // when
        MenuEntry result = underTest.upsertAndGet(dto, section);

        // then
        verify(menuEntryRepository, never()).findByIdAndSectionMenuRestaurantId(any(), any());
        verify(menuEntryMapper, never()).mapToUpdateEntity(any(), any());

        assertEquals(expectedResult, result);
        assertEquals(result.getSection(), section);
    }

    @Test
    public void shouldUpdateMenuEntry() {
        // given
        BigDecimal newPrice = BigDecimal.valueOf(30);
        Integer newPosition = 2;

        DishClientDTO newDish = buildDishClientDTO(UUID.randomUUID());
        MenuEntryDTO dto = buildDto(ENTRY_ID, newDish, newPrice, newPosition);
        MenuEntry entity = buildEntity(ENTRY_ID, UUID.randomUUID(), ENTRY_PRICE, ENTRY_POSITION);
        MenuEntry expectedResult = buildEntity(ENTRY_ID, newDish.id(), newPrice, newPosition);
        MenuSection section = new MenuSection();

        when(menuEntryRepository.findByIdAndSectionMenuRestaurantId(ENTRY_ID, RESTAURANT_ID))
                .thenReturn(Optional.of(entity));
        when(menuEntryMapper.mapToUpdateEntity(dto, entity)).thenReturn(expectedResult);
        // when
        MenuEntry result = underTest.upsertAndGet(dto, section);

        // then
        verify(menuEntryMapper, never()).mapToSaveEntity(any());

        assertEquals(result, expectedResult);
        assertEquals(result.getSection(), section);
    }

    @Test
    public void shouldThrowExceptionWhenEntryNotFound() {
        // given
        DishClientDTO dish = buildDishClientDTO(UUID.randomUUID());
        MenuEntryDTO dto = buildDto(ENTRY_ID, dish, ENTRY_PRICE, ENTRY_POSITION);
        MenuSection section = new MenuSection();

        when(menuEntryRepository.findByIdAndSectionMenuRestaurantId(ENTRY_ID, RESTAURANT_ID)).thenReturn(Optional.empty());
        // when
        assertThrows(MenuEntryNotFoundException.class, () -> underTest.upsertAndGet(dto, section));

        // then
        verify(menuEntryMapper, never()).mapToUpdateEntity(any(), any());
        verify(menuEntryMapper, never()).mapToSaveEntity(any());
    }

    private MenuEntry buildEntity(UUID id, UUID dishId, BigDecimal price, Integer position) {
        MenuEntry entity = new MenuEntry();
        entity.setId(id);
        entity.setDishId(dishId);
        entity.setPrice(price);
        entity.setPosition(position);
        return entity;
    }

    private MenuEntryDTO buildDto(UUID id, DishClientDTO dish, BigDecimal price, Integer position) {
        MenuEntryDTO dto = new MenuEntryDTO();
        dto.setId(id);
        dto.setDish(dish);
        dto.setPrice(price);
        dto.setPosition(position);
        return dto;
    }

    private DishClientDTO buildDishClientDTO(UUID id) {
        DishClientDTO dto = new DishClientDTO();
        dto.setId(id);
        return dto;
    }
}