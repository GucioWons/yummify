package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.menu.MenuDTO;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public MenuDTO create(MenuDTO dto) {
        Menu entity = menuRepository.save(menuMapper.mapToEntity(dto));
        return menuMapper.mapToDTO(entity);
    }
}
