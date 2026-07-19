package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItem {
    private final Id id;
    private final DishId dishId;
    private final DishSnapshot dishSnapshot;
    private int quantity;

    public static OrderItem create(DishId dishId, DishSnapshot dishSnapshot, Integer quantity) {
        return new OrderItem(Id.random(), dishId, dishSnapshot, quantity);
    }

    public OrderItem increaseQuantity(int quantity) {
        this.quantity += quantity;
        return this;
    }

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
        public static DishSnapshot of(TranslatedString name, BigDecimal price) {
            return new DishSnapshot(name, price);
        }
    }
}
