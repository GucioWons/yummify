package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.port.DishImageManagementPort;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.common.file.domain.fixture.FileDomainFixture.givenFile;
import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenUpdateDishImageCommand;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateDishImageUsecaseTest {
    private final DishLookupService dishLookupService = mock(DishLookupService.class);
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishImageManagementPort dishImageManagementPort = mock(DishImageManagementPort.class);

    private final UpdateDishImageUsecase underTest = new UpdateDishImageUsecase(
            dishLookupService,
            dishRepository,
            dishImageManagementPort
    );

    @Test
    void shouldUpdateDishImage() {
        // given
        var command = givenUpdateDishImageCommand();
        var dish = givenDish(1);
        var image = givenFile(1);
        dish.changeImage(image);

        when(dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(dish);

        // when
        var result = underTest.updateImage(command);

        // then
        verify(dishLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(dishImageManagementPort).update(image, dish.getRestaurantId(), command.image());
        verify(dishRepository).save(any());

        assertThat(result).isEqualTo(dish.getImage());
    }
}
