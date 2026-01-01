package com.guciowons.yummify.common.core.validation;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EntityIdValidator implements ConstraintValidator<CheckPositionedList, BaseEntityDTO> {
    @Override
    public boolean isValid(BaseEntityDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.id() != null;
    }
}
