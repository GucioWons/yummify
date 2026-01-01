package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.PublicOtpService;
import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.table.infrastructure.repository.TableRepository;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TableAuthServiceTest {
    @InjectMocks
    private TableAuthService underTest;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private PublicOtpService otpService;

    private final UUID RESTAURANT_ID = UUID.randomUUID();
    private final Language LANGUAGE = Language.EN;
    private final UUID TABLE_ID = UUID.randomUUID();

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
    void shouldGenerateOtp() {
        // given
        UUID userId = UUID.randomUUID();
        String name = "A01";
        String otp = "otp";

        Table table = buildEntity(userId, name);
        OtpDTO expectedResult = new OtpDTO(name, otp);

        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.of(table));
        when(otpService.createOtp(userId)).thenReturn(expectedResult);

        // when
        OtpDTO result = underTest.generateOtp(TABLE_ID);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowWhenTableNotFound() {
        // given
        when(tableRepository.findByIdAndRestaurantId(TABLE_ID, RESTAURANT_ID)).thenReturn(Optional.empty());

        // when
        assertThrows(TableNotFoundException.class, () -> underTest.generateOtp(TABLE_ID));

        // then
        verify(otpService, never()).createOtp(any());
    }

    private Table buildEntity(UUID userId, String name) {
        Table table = new Table();
        table.setId(TABLE_ID);
        table.setRestaurantId(RESTAURANT_ID);
        table.setUserId(userId);
        table.setName(name);
        return table;
    }
}