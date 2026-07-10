package com.guciowons.yummify.common.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    OWNER(null),

    RESTAURANT_CREATE(OWNER),
    RESTAURANT_MODIFY(OWNER),

    TABLE(OWNER),
    TABLE_READ(TABLE),
    TABLE_CREATE(TABLE),
    TABLE_MODIFY(TABLE),
    TABLE_GENERATE_OTP(TABLE),

    INGREDIENT(OWNER),
    INGREDIENT_READ(INGREDIENT),
    INGREDIENT_CREATE(INGREDIENT),
    INGREDIENT_MODIFY(INGREDIENT),

    DISH(OWNER),
    DISH_READ(DISH),
    DISH_CREATE(DISH),
    DISH_MODIFY(DISH),

    MENU(OWNER),
    MENU_READ(MENU),
    MENU_CREATE(MENU),
    MENU_MODIFY(MENU),
    MENU_PUBLISH(MENU),
    MENU_RESTORE(MENU),

    ORDER(OWNER),
    ORDER_READ(ORDER),
    ORDER_CREATE(ORDER);

    private final Permission parent;

    public boolean implies(Permission permission) {
        Permission current = permission;

        while (current != null) {
            if (current == this) {
                return true;
            }
            current = current.parent;
        }

        return false;
    }
}
