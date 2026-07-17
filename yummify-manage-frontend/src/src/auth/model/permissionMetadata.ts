import {Dtos} from "../../common/dtos.ts";
import Permission = Dtos.Permission;

export interface PermissionMetadata {
    parent?: Permission,
    isHidden?: boolean,
    translation: string
}

export const PermissionMetadata: Record<Permission, PermissionMetadata> = {
    [Permission.ADMIN]: {
        isHidden: true,
        translation: "permission.admin",
    },
    [Permission.OWNER]: {
        isHidden: true,
        parent: Permission.ADMIN,
        translation: "permission.owner",
    },

    [Permission.USER]: {
        parent: Permission.OWNER,
        translation: "permission.user",
    },
    [Permission.USER_READ]: {
        parent: Permission.USER,
        translation: "permission.user.read",
    },
    [Permission.USER_CREATE]: {
        parent: Permission.USER,
        translation: "permission.user.create",
    },

    [Permission.ROLE]: {
        parent: Permission.OWNER,
        translation: "permission.role",
    },
    [Permission.ROLE_READ]: {
        parent: Permission.ROLE,
        translation: "permission.role.read",
    },
    [Permission.ROLE_CREATE]: {
        parent: Permission.ROLE,
        translation: "permission.role.create",
    },

    [Permission.RESTAURANT]: {
        parent: Permission.OWNER,
        translation: "permission.restaurant",
    },

    [Permission.RESTAURANT_MODIFY]: {
        parent: Permission.RESTAURANT,
        translation: "permission.restaurant.modify",
    },

    [Permission.TABLE]: {
        parent: Permission.OWNER,
        translation: "permission.table",
    },
    [Permission.TABLE_READ]: {
        parent: Permission.TABLE,
        translation: "permission.table.read",
    },
    [Permission.TABLE_CREATE]: {
        parent: Permission.TABLE,
        translation: "permission.table.create",
    },
    [Permission.TABLE_MODIFY]: {
        parent: Permission.TABLE,
        translation: "permission.table.modify",
    },
    [Permission.TABLE_GENERATE_OTP]: {
        parent: Permission.TABLE,
        translation: "permission.table.generateOtp",
    },

    [Permission.INGREDIENT]: {
        parent: Permission.OWNER,
        translation: "permission.ingredient",
    },
    [Permission.INGREDIENT_READ]: {
        parent: Permission.INGREDIENT,
        translation: "permission.ingredient.read",
    },
    [Permission.INGREDIENT_CREATE]: {
        parent: Permission.INGREDIENT,
        translation: "permission.ingredient.create",
    },
    [Permission.INGREDIENT_MODIFY]: {
        parent: Permission.INGREDIENT,
        translation: "permission.ingredient.modify",
    },

    [Permission.DISH]: {
        parent: Permission.OWNER,
        translation: "permission.dish",
    },
    [Permission.DISH_READ]: {
        parent: Permission.DISH,
        translation: "permission.dish.read",
    },
    [Permission.DISH_CREATE]: {
        parent: Permission.DISH,
        translation: "permission.dish.create",
    },
    [Permission.DISH_MODIFY]: {
        parent: Permission.DISH,
        translation: "permission.dish.modify",
    },

    [Permission.MENU]: {
        parent: Permission.OWNER,
        translation: "permission.menu",
    },
    [Permission.MENU_READ]: {
        parent: Permission.MENU,
        translation: "permission.menu.read",
    },
    [Permission.MENU_CREATE]: {
        parent: Permission.MENU,
        translation: "permission.menu.create",
    },
    [Permission.MENU_MODIFY]: {
        parent: Permission.MENU,
        translation: "permission.menu.modify",
    },
    [Permission.MENU_PUBLISH]: {
        parent: Permission.MENU,
        translation: "permission.menu.publish",
    },
    [Permission.MENU_RESTORE]: {
        parent: Permission.MENU,
        translation: "permission.menu.restore",
    },

    [Permission.ORDER]: {
        parent: Permission.OWNER,
        translation: "permission.order",
    },
    [Permission.ORDER_READ]: {
        parent: Permission.ORDER,
        translation: "permission.order.read",
    },
    [Permission.ORDER_CREATE]: {
        parent: Permission.ORDER,
        translation: "permission.order.create",
    },
};