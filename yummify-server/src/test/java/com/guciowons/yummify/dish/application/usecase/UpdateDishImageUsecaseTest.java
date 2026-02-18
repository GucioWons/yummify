package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.application.service.DishUpdateImageService;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.givenUpdateDishImageCommand;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDish;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishImageId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateDishImageUsecaseTest {
    private final DishLookupService dishLookupService = mock(DishLookupService.class);
    private final DishRepository dishRepository = mock(DishRepository.class);
    private final DishUpdateImageService dishUpdateImageService = mock(DishUpdateImageService.class);

    private final UpdateDishImageUsecase underTest = new UpdateDishImageUsecase(
            dishLookupService,
            dishRepository,
            dishUpdateImageService
    );

    @Test
    void shouldUpdateDishImage() {
        // given
        var command = givenUpdateDishImageCommand();
        var dish = givenDish(1);
        var imageId = givenDishImageId(1);
        dish.changeImage(imageId);

        when(dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(dish);

        // when
        var result = underTest.updateImage(command);

        // then
        verify(dishLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(dishUpdateImageService).updateImage(dish, command.image());
        verify(dishRepository).save(any());

        assertThat(result).isEqualTo(dish.getImageId());
    }
}
