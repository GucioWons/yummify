package com.guciowons.yummify.menu;

import java.math.BigDecimal;
import java.util.UUID;

public interface PublicMenuFacadePort {
    BigDecimal getPriceByDishId(UUID restaurantId, UUID dishId);
}
