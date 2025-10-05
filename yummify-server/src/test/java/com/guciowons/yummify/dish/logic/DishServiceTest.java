package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {
    @InjectMocks
    private DishService underTest;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;

    private final UUID DISH_ID = UUID.randomUUID();

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
    void shouldNotGetIngredientAndThrowExceptionWhenIngredientNotFound() {
        // given
        when(dishRepository.findByIdAndRestaurantId(DISH_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(DishNotFoundException.class, () -> underTest.getById(DISH_ID));

        // then
        verify(dishMapper, never()).mapToManageDTO(any());
    }
}