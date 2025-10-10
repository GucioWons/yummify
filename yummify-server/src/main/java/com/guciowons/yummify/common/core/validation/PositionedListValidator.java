package com.guciowons.yummify.common.core.validation;

import com.guciowons.yummify.common.core.dto.PositionedDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;

public class PositionedListValidator implements ConstraintValidator<CheckPositionedList, List<PositionedDTO>> {
    @Override
    public boolean isValid(List<PositionedDTO> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        List<Integer> positions = value.stream()
                .map(PositionedDTO::getPosition)
                .sorted()
                .toList();

        Set<Integer> uniquePositions = new HashSet<>(positions);
        if (uniquePositions.size() != positions.size()) {
            return false;
        }

        for (int i = 1; i < positions.size(); i++) {
            if (positions.get(i) != positions.get(i - 1) + 1) {
                return false;
            }
        }

        return true;
    }
}
