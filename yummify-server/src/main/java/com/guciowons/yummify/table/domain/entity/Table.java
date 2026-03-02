package com.guciowons.yummify.table.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Table {
    private Id id;
    private RestaurantId restaurantId;
    private UserId userId;
    private Name name;
    private Capacity capacity;

    public static Table create(RestaurantId restaurantId, Name name, Capacity capacity) {
        return new Table(Id.random(), restaurantId, null, name, capacity);
    }

    public void updateDetails(Name name, Capacity capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void changeUser(UserId userId) {
        this.userId = userId;
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id random() {
            return new Id(UUID.randomUUID());
        }

        public static Id of(UUID value) {
            return new Id(value);
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }

    public record UserId(UUID value) implements IdValueObject {
        public static UserId of(UUID value) {
            return new UserId(value);
        }
    }

    public record Name(String value) implements ValueObject<String> {
        public static Name of(String value) {
            return new Name(value);
        }
    }

    public record Capacity(Integer value) implements ValueObject<Integer> {
        public static Capacity of(Integer value) {
            return new Capacity(value);
        }
    }
}
