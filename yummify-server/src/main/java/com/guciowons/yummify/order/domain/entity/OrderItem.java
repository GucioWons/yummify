package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(Id id, DishSnapshot dishSnapshot, Integer quantity) {
    public record Id(UUID value) implements IdValueObject {
        public static Id of(UUID value) {
            return new Id(value);
        }

        public static Id random() {
            return of(UUID.randomUUID());
        }
    }

    public record DishId(UUID value) implements IdValueObject {
        public static DishId of(UUID value) {
            return new DishId(value);
        }
    }

    public record DishSnapshot(TranslatedString name, BigDecimal price) {
    }
}
