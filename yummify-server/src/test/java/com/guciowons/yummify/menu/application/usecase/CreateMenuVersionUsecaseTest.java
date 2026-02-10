package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.exception.MenuVersionAlreadyExistsException;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.menu.application.fixture.MenuApplicationFixture.givenCreateMenuVersionCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CreateMenuVersionUsecaseTest {
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final CreateMenuVersionUsecase underTest = new CreateMenuVersionUsecase(menuVersionRepository);

    @Test
    void shouldCreateMenuVersion_WhenNoDraftExists() {
        // given
        var command = givenCreateMenuVersionCommand();

        when(menuVersionRepository.existsByRestaurantId(command.restaurantId())).thenReturn(false);

        // when
        var result = underTest.create(command);

        // then
        verify(menuVersionRepository).existsByRestaurantId(command.restaurantId());
        verify(menuVersionRepository).save(result);

        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getStatus()).isEqualTo(MenuVersion.Status.DRAFT);
        assertThat(result.getVersion()).isEqualTo(1);
    }

    @Test
    void shouldNotCreateMenuVersionAndThrowException_WhenNoDraftExists() {
        // given
        var command = givenCreateMenuVersionCommand();

        when(menuVersionRepository.existsByRestaurantId(command.restaurantId())).thenReturn(true);

        // when
        assertThatThrownBy(() -> underTest.create(command))
                .isInstanceOf(MenuVersionAlreadyExistsException.class);

        // then
        verify(menuVersionRepository).existsByRestaurantId(command.restaurantId());
        verify(menuVersionRepository, never()).save(any());
    }
}
