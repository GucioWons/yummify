package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.order.domain.exception.InvalidOrderItemStatusTransitionException;
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
    private OrderItemStatus status;

    public static OrderItem create(DishId dishId, DishSnapshot dishSnapshot, Integer quantity) {
        return new OrderItem(Id.random(), dishId, dishSnapshot, quantity, OrderItemStatus.NEW);
    }

    public OrderItem increaseQuantity(int quantity) {
        this.quantity += quantity;
        return this;
    }

    public void startPreparation() {
        updateStatus(OrderItemStatus.IN_PREPARATION);
    }

    public void cancel() {
        updateStatus(OrderItemStatus.CANCELLED);
    }

    private void updateStatus(OrderItemStatus newStatus) {
        if (!newStatus.canTransitionFrom(this.status)) {
            throw new InvalidOrderItemStatusTransitionException(this.status, newStatus);
        }
        this.status = newStatus;
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
