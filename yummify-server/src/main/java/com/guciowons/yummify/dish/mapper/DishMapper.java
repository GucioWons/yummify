package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.common.temp.mapper.TranslatableMapper;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.DishDTO;
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
public abstract class DishMapper implements TranslatableMapper<Dish, DishDTO, DishManageDTO, DishClientDTO> {
    @Autowired
    private IngredientRepository ingredientRepository;

    public abstract DishManageDTO mapToManageDTO(Dish entity);
    public abstract DishClientDTO mapToClientDTO(Dish entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToSaveEntity(DishManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToUpdateEntity(DishManageDTO dto, @MappingTarget Dish entity);

    @AfterMapping
    protected void setIngredientsList(DishManageDTO dto, @MappingTarget Dish entity) {
        if (entity.getIngredients() == null) {
            entity.setIngredients(new ArrayList<>());
        } else {
            entity.getIngredients().clear();
        }

        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        List<UUID> missing = new ArrayList<>();

        for (IngredientClientDTO ingredientDTO : dto.getIngredients()) {
            ingredientRepository.findByIdAndRestaurantId(ingredientDTO.getId(), restaurantId)
                    .ifPresentOrElse(entity.getIngredients()::add, () -> missing.add(ingredientDTO.getId()));
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
