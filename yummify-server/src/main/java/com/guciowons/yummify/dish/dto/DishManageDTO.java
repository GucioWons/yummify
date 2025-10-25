package com.guciowons.yummify.dish.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class DishManageDTO extends BaseEntityDTO {
    @NotNull
    private TranslatedStringDTO name;

    private TranslatedStringDTO description;

    @Valid
    @NotNull
    private List<IngredientClientDTO> ingredients;

    private MultipartFile image;

    private String imageUrl;
}
