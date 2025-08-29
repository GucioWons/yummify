package com.guciowons.yummify.dish.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Dish;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public abstract class DishMapper {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Mapping(target = "description", expression = "java(entity.getDescription().get())")
    public abstract DishDTO<String> mapToClientDTO(Dish dish);

    public abstract DishDTO<TranslatedStringDTO> mapToAdminDTO(Dish dish);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToEntity(DishDTO<TranslatedStringDTO> dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract Dish mapToUpdateEntity(DishDTO<TranslatedStringDTO> dto, @MappingTarget Dish dish);

    @AfterMapping
    protected void setIngredientsList(DishDTO<TranslatedStringDTO> dto, @MappingTarget Dish dish) {
        if (dish.getIngredients() == null) {
            dish.setIngredients(new ArrayList<>());
        } else {
            dish.getIngredients().clear();
        }

        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        List<UUID> notFoundIds = new ArrayList<>();

        dto.getIngredients()
                .forEach(ingredientDTO -> ingredientRepository.findByIdAndRestaurantId(ingredientDTO.getId(), restaurantId)
                        .ifPresentOrElse(
                                dish.getIngredients()::add,
                                () -> notFoundIds.add(ingredientDTO.getId())
                        ));

        if (!notFoundIds.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
