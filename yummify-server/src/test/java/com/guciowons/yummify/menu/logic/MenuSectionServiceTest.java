package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.TranslatedStringHelper;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuSectionRepository;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.entity.MenuEntry;
import com.guciowons.yummify.menu.entity.MenuSection;
import com.guciowons.yummify.menu.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.mapper.MenuSectionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuSectionServiceTest {
    @InjectMocks
    private MenuSectionService underTest;

    @Mock
    private MenuEntryService menuEntryService;

    @Mock
    private MenuSectionRepository menuSectionRepository;

    @Mock
    private MenuSectionMapper menuSectionMapper;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

    private final UUID SECTION_ID = UUID.randomUUID();
    private final String SECTION_NAME = "section";

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
    void shouldCreateNewMenuSection() {
        // given
        MenuSectionManageDTO dto = buildDto(null, SECTION_NAME, List.of());
        MenuSection expectedResult = buildEntity(null, SECTION_NAME, new ArrayList<>());
        Menu menu = new Menu();

        when(menuSectionMapper.mapToSaveEntity(dto)).thenReturn(expectedResult);

        // when
        MenuSection result = underTest.upsertAndGet(dto, menu);

        // then
        verify(menuSectionMapper, never()).mapToUpdateEntity(any(), any());
        verify(menuSectionRepository, never()).findByIdAndMenuRestaurantId(any(), any());

        assertEquals(menu, result.getMenu());
    }

    @Test
    void shouldUpdateExistingMenuSectionWithNewEntryList() {
        // given
        String newName = "updated section";
        UUID firstEntryId = UUID.randomUUID();
        UUID secondEntryId = UUID.randomUUID();

        MenuEntryDTO firstEntryDTO = buildEntryDTO(firstEntryId);
        MenuEntryDTO secondEntryDTO = buildEntryDTO(secondEntryId);
        MenuEntry firstEntry = buildEntryEntity(firstEntryId);
        MenuEntry secondEntry = buildEntryEntity(secondEntryId);
        MenuSectionManageDTO dto = buildDto(SECTION_ID, newName, new ArrayList<>(List.of(firstEntryDTO, secondEntryDTO)));
        MenuSection existingEntity = buildEntity(SECTION_ID, SECTION_NAME, null);
        MenuSection updatedEntity = buildEntity(SECTION_ID, newName, null);
        Menu menu = new Menu();

        when(menuSectionRepository.findByIdAndMenuRestaurantId(SECTION_ID, RESTAURANT_ID))
                .thenReturn(Optional.of(existingEntity));
        when(menuSectionMapper.mapToUpdateEntity(dto, existingEntity)).thenReturn(updatedEntity);
        when(menuEntryService.upsertAndGet(firstEntryDTO, updatedEntity)).thenReturn(firstEntry);
        when(menuEntryService.upsertAndGet(secondEntryDTO, updatedEntity)).thenReturn(secondEntry);

        // when
        MenuSection result = underTest.upsertAndGet(dto, menu);

        // then
        verify(menuSectionMapper).mapToUpdateEntity(dto, existingEntity);
        verify(menuSectionMapper, never()).mapToSaveEntity(any());

        assertEquals(menu, result.getMenu());
        assertThat(result.getEntries(), hasSize(2));
        assertThat(result.getEntries(), containsInAnyOrder(firstEntry, secondEntry));
    }

    @Test
    void shouldUpdateExistingMenuSectionWithClearedEntryList() {
        // given
        UUID oldEntryId = UUID.randomUUID();
        UUID newEntryId = UUID.randomUUID();

        MenuEntryDTO newEntryDTO = buildEntryDTO(newEntryId);
        MenuEntry oldEntry = buildEntryEntity(oldEntryId);
        MenuEntry newEntry = buildEntryEntity(newEntryId);
        MenuSectionManageDTO dto = buildDto(SECTION_ID, SECTION_NAME, new ArrayList<>(List.of(newEntryDTO)));
        MenuSection existingEntity = buildEntity(SECTION_ID, SECTION_NAME, new ArrayList<>(List.of(oldEntry)));
        MenuSection updatedEntity = buildEntity(SECTION_ID, SECTION_NAME, new ArrayList<>(List.of(oldEntry)));
        Menu menu = new Menu();

        when(menuSectionRepository.findByIdAndMenuRestaurantId(SECTION_ID, RESTAURANT_ID))
                .thenReturn(Optional.of(existingEntity));
        when(menuSectionMapper.mapToUpdateEntity(dto, existingEntity)).thenReturn(updatedEntity);
        when(menuEntryService.upsertAndGet(newEntryDTO, updatedEntity)).thenReturn(newEntry);

        // when
        MenuSection result = underTest.upsertAndGet(dto, menu);

        // then
        verify(menuSectionMapper).mapToUpdateEntity(dto, existingEntity);
        verify(menuSectionMapper, never()).mapToSaveEntity(any());

        assertEquals(menu, result.getMenu());
        assertThat(result.getEntries(), hasSize(1));
        assertThat(result.getEntries(), containsInAnyOrder(newEntry));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingSection() {
        // given
        MenuSectionManageDTO dto = buildDto(SECTION_ID, SECTION_NAME, new ArrayList<>());
        Menu menu = new Menu();

        when(menuSectionRepository.findByIdAndMenuRestaurantId(SECTION_ID, RESTAURANT_ID))
                .thenReturn(Optional.empty());

        // when
        assertThrows(MenuSectionNotFoundException.class, () -> underTest.upsertAndGet(dto, menu));

        // then
        verify(menuSectionMapper, never()).mapToUpdateEntity(any(), any());
        verify(menuSectionMapper, never()).mapToSaveEntity(any());
    }

    private MenuSectionManageDTO buildDto(UUID id, String name, List<MenuEntryDTO> entries) {
        MenuSectionManageDTO dto = new MenuSectionManageDTO();
        dto.setId(id);
        dto.setName(TranslatedStringHelper.buildDTO(name, LANGUAGE));
        dto.setEntries(entries);
        return dto;
    }

    private MenuSection buildEntity(UUID id, String name, List<MenuEntry> entries) {
        MenuSection entity = new MenuSection();
        entity.setId(id);
        entity.setName(TranslatedStringHelper.buildEntity(name, LANGUAGE));
        entity.setEntries(entries);
        return entity;
    }

    private MenuEntryDTO buildEntryDTO(UUID id) {
        MenuEntryDTO dto = new MenuEntryDTO();
        dto.setId(id);
        return dto;
    }

    private MenuEntry buildEntryEntity(UUID id) {
        MenuEntry entity = new MenuEntry();
        entity.setId(id);
        return entity;
    }
}
