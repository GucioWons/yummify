package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.exception.MenuIsActiveException;
import com.guciowons.yummify.menu.exception.MenuIsNotActiveException;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuActiveServiceTest {
    @InjectMocks
    private MenuActiveService underTest;

    @Mock
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

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

    @Test
    public void shouldGetActiveMenu() {
        // given
        Menu entity = new Menu();
        MenuClientDTO clientDTO = new MenuClientDTO();

        when(menuRepository.findByRestaurantIdAndActive(RESTAURANT_ID, true)).thenReturn(Optional.of(entity));
        when(menuMapper.mapToClientDTO(entity)).thenReturn(clientDTO);

        // when
        MenuClientDTO result = underTest.getActive();

        // then
        assertEquals(clientDTO, result);
    }

    @Test
    public void shouldActivateMenu() {
        // given
        UUID menuId = UUID.randomUUID();

        Menu menu = new Menu();
        menu.setRestaurantId(RESTAURANT_ID);
        MenuManageDTO manageDTO = new MenuManageDTO();

        when(menuService.getEntityById(menuId)).thenReturn(menu);
        when(menuRepository.findByRestaurantIdAndActive(RESTAURANT_ID, true)).thenReturn(Optional.empty());
        when(menuRepository.save(menu)).thenReturn(menu);
        when(menuMapper.mapToManageDTO(menu)).thenReturn(manageDTO);

        // when
        MenuManageDTO result = underTest.activate(menuId);

        // then
        verify(menuRepository).save(menu);

        assertEquals(manageDTO, result);
        assertTrue(menu.isActive());
    }

    @Test
    public void shouldNotActivateMenuAndThrowExceptionWhenMenuAlreadyActive() {
        // given
        UUID menuId = UUID.randomUUID();

        Menu menu = new Menu();
        menu.setRestaurantId(RESTAURANT_ID);
        menu.setActive(true);

        when(menuService.getEntityById(menuId)).thenReturn(menu);

        // when
        assertThrows(MenuIsActiveException.class, () -> underTest.activate(menuId));

        // then
        verify(menuRepository, never()).findByRestaurantIdAndActive(any(), anyBoolean());
        verify(menuRepository, never()).save(any());
        verify(menuMapper, never()).mapToManageDTO(any());

        assertTrue(menu.isActive());
    }

    @Test
    public void shouldActivateMenuAndDeactivateCurrentOne() {
        // given
        UUID menuId = UUID.randomUUID();

        Menu menu = new Menu();
        menu.setRestaurantId(RESTAURANT_ID);
        Menu current = new Menu();
        current.setRestaurantId(RESTAURANT_ID);
        current.setActive(true);
        MenuManageDTO manageDTO = new MenuManageDTO();

        when(menuService.getEntityById(menuId)).thenReturn(menu);
        when(menuRepository.findByRestaurantIdAndActive(RESTAURANT_ID, true)).thenReturn(Optional.of(current));
        when(menuRepository.save(menu)).thenReturn(menu);
        when(menuMapper.mapToManageDTO(menu)).thenReturn(manageDTO);

        // when
        MenuManageDTO result = underTest.activate(menuId);

        // then
        verify(menuRepository).save(menu);

        assertEquals(manageDTO, result);
        assertTrue(menu.isActive());
        assertFalse(current.isActive());
    }

    @Test
    public void shouldDeactivateMenu() {
        // given
        UUID menuId = UUID.randomUUID();

        Menu menu = new Menu();
        menu.setActive(true);
        MenuManageDTO manageDTO = new MenuManageDTO();

        when(menuService.getEntityById(menuId)).thenReturn(menu);
        when(menuMapper.mapToManageDTO(menu)).thenReturn(manageDTO);
        when(menuRepository.save(menu)).thenReturn(menu);

        // when
        MenuManageDTO result = underTest.deactivate(menuId);

        // then
        verify(menuRepository).save(menu);

        assertEquals(manageDTO, result);
        assertFalse(menu.isActive());
    }

    @Test
    public void shouldNotDeactivateMenuAndThrowExceptionWhenMenuAlreadyDeactivated() {
        // given
        UUID menuId = UUID.randomUUID();

        Menu menu = new Menu();

        when(menuService.getEntityById(menuId)).thenReturn(menu);

        // when
        assertThrows(MenuIsNotActiveException.class, () -> underTest.deactivate(menuId));

        // then
        verify(menuMapper, never()).mapToManageDTO(any());
        verify(menuRepository, never()).save(any());

        assertFalse(menu.isActive());
    }
}
