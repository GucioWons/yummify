package com.guciowons.yummify.order.domain.exception.message;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderErrorMessage implements ErrorMessage {
    ORDER_TABLE_NOT_FOUND_BY_ID("Could not find table with ID '{{id}}' for order"),
    ORDER_NOT_FOUND_BY_ID("Could not find order with ID '{{id}}'"),
    ORDER_ITEM_NOT_FOUND_BY_ID("Could not find order item with ID '{{id}}'"),;

    private final String message;
}
