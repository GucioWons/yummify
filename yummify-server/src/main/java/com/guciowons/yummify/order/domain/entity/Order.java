package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.order.domain.exception.OrderItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {
    private final Id id;
    private final RestaurantId restaurantId;
    private final TableId tableId;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;

    public static Order create(RestaurantId restaurantId, TableId tableId) {
        return new Order(Id.random(), restaurantId, tableId, OrderStatus.NEW);
    }

    public OrderItem addItem(OrderItem.DishId dishId, OrderItem.DishSnapshot dishSnapshot, Integer quantity) {
        return items.stream()
                .filter(item -> item.getDishId().equals(dishId))
                .findAny()
                .map(item -> item.increaseQuantity(quantity))
                .orElseGet(() -> createAndAddItem(dishId, dishSnapshot, quantity));
    }

    private OrderItem createAndAddItem(OrderItem.DishId dishId, OrderItem.DishSnapshot dishSnapshot, Integer quantity) {
        OrderItem newItem = OrderItem.create(dishId, dishSnapshot, quantity);
        items.add(newItem);
        return newItem;
    }

    public void deleteItem(OrderItem.Id orderItemId) {
        boolean removed = items.removeIf(item -> item.getId().equals(orderItemId));

        if (!removed) {
            throw new OrderItemNotFoundException(orderItemId);
        }
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id of(UUID value) {
            return new Id(value);
        }

        public static Id random() {
            return of(UUID.randomUUID());
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }

    public record TableId(UUID value) implements IdValueObject {
        public static TableId of(UUID value) {
            return new TableId(value);
        }
    }
}
