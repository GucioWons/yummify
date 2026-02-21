package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import com.guciowons.yummify.dish.application.model.mapper.DishCommandMapper;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.guciowons.yummify.dish.application.fixture.DishApplicationFixture.*;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishFacadeTest {
    private final CreateDishUsecase createDishUsecase = mock(CreateDishUsecase.class);
    private final GetAllDishesUsecase getAllDishesUsecase = mock(GetAllDishesUsecase.class);
    private final GetDishUsecase getDishUsecase = mock(GetDishUsecase.class);
    private final UpdateDishUsecase updateDishUsecase = mock(UpdateDishUsecase.class);
    private final UpdateDishImageUsecase updateDishImageUsecase = mock(UpdateDishImageUsecase.class);
    private final DomainExceptionHandler dishDomainExceptionHandler = mock(DomainExceptionHandler.class);
    private final DishCommandMapper dishCommandMapper = mock(DishCommandMapper.class);

    private final DishFacade underTest = new DishFacade(
            createDishUsecase,
            getAllDishesUsecase,
            getDishUsecase,
            updateDishUsecase,
            updateDishImageUsecase,
            dishDomainExceptionHandler,
            dishCommandMapper
    );

    @Test
    void shouldCreateDish() {
        // given
        var restaurantId = givenDishRestaurantId(1).value();
        var name = Map.of("EN", "Spaghetti");
        var description = Map.of("EN", "Pasta with sauce");
        var ingredientIds = givenDishIngredientIds(1);
        var command = givenCreateDishCommand();
        var dish = givenDish(1);

        when(dishCommandMapper.toCreateDishCommand(restaurantId, name, description, ingredientIds)).thenReturn(command);
        when(dishDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Dish>>any()))
                .thenAnswer(inv -> inv.<Supplier<Dish>>getArgument(0).get());
        when(createDishUsecase.create(command)).thenReturn(dish);

        // when
        var result = underTest.create(restaurantId, name, description, ingredientIds);

        // then
        verify(dishCommandMapper).toCreateDishCommand(restaurantId, name, description, ingredientIds);
        verify(dishDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Dish>>any());
        verify(createDishUsecase).create(command);

        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldGetAllIngredients() {
        // given
        var restaurantId = givenDishRestaurantId(1).value();
        var command = givenGetAllDishesCommand();
        var dishes = List.of(givenDish(1), givenDish(2), givenDish(3));

        when(dishCommandMapper.toGetAllDishesCommand(restaurantId)).thenReturn(command);
        when(getAllDishesUsecase.getAll(command)).thenReturn(dishes);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(dishCommandMapper).toGetAllDishesCommand(restaurantId);
        verify(getAllDishesUsecase).getAll(command);

        assertThat(result).isEqualTo(dishes);
    }

    @Test
    void shouldGetIngredient() {
        // given
        var dishId = givenDishId(1).value();
        var restaurantId = givenDishRestaurantId(1).value();
        var command = givenGetDishCommand();
        var dish = givenDish(1);

        when(dishCommandMapper.toGetDishCommand(dishId, restaurantId)).thenReturn(command);
        when(dishDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Dish>>any()))
                .thenAnswer(inv -> inv.<Supplier<Dish>>getArgument(0).get());
        when(getDishUsecase.getById(command)).thenReturn(dish);

        // when
        var result = underTest.getById(dishId, restaurantId);

        // then
        verify(dishCommandMapper).toGetDishCommand(dishId, restaurantId);
        verify(dishDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Dish>>any());
        verify(getDishUsecase).getById(command);

        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldUpdateIngredient() {
        // given
        var dishId = givenDishId(1).value();
        var restaurantId = givenDishRestaurantId(1).value();
        var name = Map.of("EN", "Spaghetti");
        var description = Map.of("EN", "Pasta with sauce");
        var ingredientIds = givenDishIngredientIds(1);
        var command = givenUpdateDishCommand();
        var dish = givenDish(1);

        when(dishCommandMapper.toUpdateDishCommand(dishId, restaurantId, name, description, ingredientIds))
                .thenReturn(command);
        when(dishDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Dish>>any()))
                .thenAnswer(inv -> inv.<Supplier<Dish>>getArgument(0).get());
        when(updateDishUsecase.update(command)).thenReturn(dish);

        // when
        var result = underTest.update(dishId, restaurantId, name, description, ingredientIds);

        // then
        verify(dishCommandMapper).toUpdateDishCommand(dishId, restaurantId, name, description, ingredientIds);
        verify(dishDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Dish>>any());
        verify(updateDishUsecase).update(command);

        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldUpdateDishImage() {
        // given
        var dishId = givenDishId(1).value();
        var restaurantId = givenDishRestaurantId(1).value();
        var image = mock(MultipartFile.class);
        var command = givenUpdateDishImageCommand();
        var imageId = givenDishImageId(1);

        when(dishCommandMapper.toUpdateDishImageCommand(dishId, image, restaurantId)).thenReturn(command);
        when(dishDomainExceptionHandler.handle(ArgumentMatchers.<Supplier<Dish.ImageId>>any()))
                .thenAnswer(inv -> inv.<Supplier<Dish.ImageId>>getArgument(0).get());
        when(updateDishImageUsecase.updateImage(command)).thenReturn(imageId);

        // when
        var result = underTest.updateImage(dishId, restaurantId, image);

        // then
        verify(dishCommandMapper).toUpdateDishImageCommand(dishId, image, restaurantId);
        verify(dishDomainExceptionHandler).handle(ArgumentMatchers.<Supplier<Dish.ImageId>>any());
        verify(updateDishImageUsecase).updateImage(command);

        assertThat(result).isEqualTo(imageId);
    }
}
