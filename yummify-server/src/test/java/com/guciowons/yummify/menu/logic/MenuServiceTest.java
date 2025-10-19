package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.PublicDishService;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.entity.MenuSection;
import com.guciowons.yummify.menu.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @InjectMocks
    private MenuService underTest;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

    @Mock
    private MenuSectionService menuSectionService;

    @Mock
    private PublicDishService dishService;

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
    void shouldValidateAllDishes() {
        // given
        UUID firstDishId = UUID.randomUUID();
        UUID secondDishId = UUID.randomUUID();

        MenuEntryDTO firstEntryDTO = buildEntryDTO(firstDishId);
        MenuEntryDTO secondEntryDTO = buildEntryDTO(secondDishId);
        MenuSectionManageDTO firstSectionDTO = buildMenuSectionDTO(List.of(firstEntryDTO));
        MenuSectionManageDTO secondSectionDTO = buildMenuSectionDTO(List.of(secondEntryDTO));
        MenuManageDTO dto = buildManageDTO(List.of(firstSectionDTO, secondSectionDTO));

        // when
        ReflectionTestUtils.invokeMethod(underTest, "validate", dto);

        // then
        verify(dishService).validateDishId(firstDishId);
        verify(dishService).validateDishId(secondDishId);
    }

    @Test
    void shouldSetNewSectionsListInAfterMappingEntity() {
        // given
        MenuSectionManageDTO firstSectionDTO = buildMenuSectionDTO(null);
        MenuSectionManageDTO secondSectionDTO = buildMenuSectionDTO(null);
        MenuSection firstSection = new MenuSection();
        MenuSection secondSection = new MenuSection();
        MenuManageDTO dto = buildManageDTO(List.of(firstSectionDTO, secondSectionDTO));
        Menu entity = buildEntity(null);

        when(menuSectionService.upsertAndGet(firstSectionDTO, entity)).thenReturn(firstSection);
        when(menuSectionService.upsertAndGet(secondSectionDTO, entity)).thenReturn(secondSection);

        // when
        ReflectionTestUtils.invokeMethod(underTest, "afterMappingEntity", dto, entity);

        // then
        assertThat(entity.getSections(), hasSize(2));
        assertThat(entity.getSections(), containsInAnyOrder(firstSection, secondSection));
    }

    @Test
    void shouldClearAndSetIngredientsListInAfterMappingEntity() {
        // given
        MenuSectionManageDTO newSectionDTO = buildMenuSectionDTO(null);
        MenuSection oldSection = new MenuSection();
        MenuSection newSection = new MenuSection();
        MenuManageDTO dto = buildManageDTO(List.of(newSectionDTO));
        Menu entity = buildEntity(new ArrayList<>(List.of(oldSection)));

        when(menuSectionService.upsertAndGet(newSectionDTO, entity)).thenReturn(newSection);

        // when
        ReflectionTestUtils.invokeMethod(underTest, "afterMappingEntity", dto, entity);

        // then
        assertThat(entity.getSections(), hasSize(1));
        assertThat(entity.getSections(), containsInAnyOrder(newSection));
    }

    @Test
    void shouldSetDishDTOsInAfterMappingManageDTO() {
        // given
        UUID firstDishId = UUID.randomUUID();
        UUID secondDishId = UUID.randomUUID();

        DishClientDTO firstDishDTO = new DishClientDTO();
        DishClientDTO secondDishDTO = new DishClientDTO();
        MenuEntryDTO firstEntryDTO = buildEntryDTO(firstDishId);
        MenuEntryDTO secondEntryDTO = buildEntryDTO(secondDishId);
        MenuSectionManageDTO firstSectionDTO = buildMenuSectionDTO(List.of(firstEntryDTO));
        MenuSectionManageDTO secondSectionDTO = buildMenuSectionDTO(List.of(secondEntryDTO));
        MenuManageDTO dto = buildManageDTO(List.of(firstSectionDTO, secondSectionDTO));
        Menu entity = buildEntity(null);

        when(dishService.getClientDTO(firstDishId)).thenReturn(firstDishDTO);
        when(dishService.getClientDTO(secondDishId)).thenReturn(secondDishDTO);

        // when
        ReflectionTestUtils.invokeMethod(underTest, "afterMappingManageDTO", dto, entity);

        // then
        assertEquals(firstDishDTO, dto.getSections().getFirst().getEntries().getFirst().getDish());
        assertEquals(secondDishDTO, dto.getSections().getLast().getEntries().getFirst().getDish());
    }

    @Test
    void shouldNotGetMenuAndThrowExceptionWhenMenuNotFound() {
        // given
        UUID id = UUID.randomUUID();

        when(menuRepository.findByIdAndRestaurantId(id, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(MenuNotFoundException.class, () -> underTest.getManageDTO(id));

        // then
        verify(menuMapper, never()).mapToManageDTO(any());
    }

    private MenuEntryDTO buildEntryDTO(UUID dishId) {
        MenuEntryDTO dto = new MenuEntryDTO();
        DishClientDTO dishDTO = new DishClientDTO();
        dishDTO.setId(dishId);
        dto.setDish(dishDTO);
        return dto;
    }

    private MenuSectionManageDTO buildMenuSectionDTO(List<MenuEntryDTO> entries) {
        MenuSectionManageDTO dto = new MenuSectionManageDTO();
        dto.setEntries(entries);
        return dto;
    }

    private MenuManageDTO buildManageDTO(List<MenuSectionManageDTO> sections) {
        MenuManageDTO dto = new MenuManageDTO();
        dto.setSections(sections);
        return dto;
    }

    private Menu buildEntity(List<MenuSection> sections) {
        Menu menu = new Menu();
        menu.setSections(sections);
        return menu;
    }
}
