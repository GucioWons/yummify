package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.DishManageDTO;
import com.guciowons.yummify.dish.IngredientClientDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.exception.IngredientsNotFoundException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {
        IngredientMapper.class,
        TranslatedStringMapper.class,
})
public abstract class DishMapper {
    @Autowired
    private IngredientRepository ingredientRepository;

    public abstract DishClientDTO mapToClientDTO(Dish dish);

    public abstract DishManageDTO mapToAdminDTO(Dish dish);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToEntity(DishManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToUpdateEntity(DishManageDTO dto, @MappingTarget Dish dish);

    @AfterMapping
    protected void setIngredientsList(DishManageDTO dto, @MappingTarget Dish dish) {
        if (dish.getIngredients() == null) {
            dish.setIngredients(new ArrayList<>());
        } else {
            dish.getIngredients().clear();
        }

        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        List<UUID> missing = new ArrayList<>();

        for (IngredientClientDTO ingredientDTO : dto.getIngredients()) {
            ingredientRepository.findByIdAndRestaurantId(ingredientDTO.getId(), restaurantId)
                    .ifPresentOrElse(dish.getIngredients()::add, () -> missing.add(ingredientDTO.getId()));
        }

        if (!missing.isEmpty()) {
            if (missing.size() > 1) {
                throw new IngredientsNotFoundException(missing);
            } else {
                throw new IngredientNotFoundException(missing.getFirst());
            }
        }
    }
}
