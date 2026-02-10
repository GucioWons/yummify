package com.guciowons.yummify.menu.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MenuEntry {
    private final Id id;
    private final DishId dishId;
    private Price price;

    public static MenuEntry create(DishId dishId, Price price) {
        return new MenuEntry(Id.random(), dishId, price);
    }

    public void update(Price price) {
        this.price = price;
    }

    public MenuEntry copy() {
        return new MenuEntry(Id.random(), dishId, price);
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id of(UUID value) {
            return new Id(value);
        }

        public static Id random() {
            return new Id(UUID.randomUUID());
        }
    }

    public record DishId(UUID value) implements IdValueObject {
        public static DishId of(UUID value) {
            return new DishId(value);
        }
    }

    public record Price(BigDecimal value) implements ValueObject<BigDecimal> {
        public static Price of(BigDecimal value) {
            return new Price(value);
        }
    }
}
