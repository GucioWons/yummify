package com.guciowons.yummify.common.security.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public enum Permission {
    ADMIN(null, "3a47d022-a6e9-417c-ad24-184745c61b02"),
    OWNER(ADMIN, "dc952287-dc57-4480-930a-cce8a941dc07"),

    USER(OWNER, "c1f25927-1432-4ad6-89e9-ddb480179d84"),
    USER_READ(USER, "47c7843f-9735-4949-89d3-f4971a0be457"),
    USER_CREATE(USER, "27e0ebd5-b9a8-4b8c-a4f4-f7977c816cc7"),

    ROLE(OWNER, "6d0a05ab-4b72-44fb-b02f-2efbccb3f71c"),
    ROLE_READ(ROLE, "3c745eb4-9180-451e-a1cc-0890c58b1bd2"),
    ROLE_CREATE(ROLE, "8abb56fe-cc8d-4a1b-9c3c-b467c9182f58"),

    RESTAURANT(OWNER, "59bc7e7e-e732-4f40-aea5-991339582bba"),
    RESTAURANT_MODIFY(OWNER, "ca934ad1-fa84-464b-b2e3-37878ff891ac"),

    TABLE(OWNER, "57d6c63c-2611-45bf-8169-963ab22513d8"),
    TABLE_READ(TABLE, "42394b8f-95fc-4543-a8df-69905358a8b0"),
    TABLE_CREATE(TABLE, "064d314b-53b8-4c31-b82b-381c64c8dbc5"),
    TABLE_MODIFY(TABLE, "7aded301-9432-41b2-a868-d2daf5268805"),
    TABLE_GENERATE_OTP(TABLE, "30238e8b-2e2a-4c96-a8f6-60b9c5dd9c87"),

    INGREDIENT(OWNER, "c440a74b-d3a7-4943-9fd6-20c21dba6cd3"),
    INGREDIENT_READ(INGREDIENT, "9b9b0202-36f0-4d42-90f1-dd1f439f655f"),
    INGREDIENT_CREATE(INGREDIENT, "f09e6a06-5fb1-4759-aa0c-d56b7bf0fd23"),
    INGREDIENT_MODIFY(INGREDIENT, "4816509b-51dd-4641-9829-192b242f71b3"),

    DISH(OWNER, "6a0b3869-7b9e-4a9f-9b37-8deab8272b7c"),
    DISH_READ(DISH, "6d1b44be-dec7-401b-8f9c-1d13fb6c2b83"),
    DISH_CREATE(DISH, "0a6e699d-c99b-41aa-8699-7a75e0591386"),
    DISH_MODIFY(DISH, "7e588807-3423-49b2-8d2f-b11030b8b5ec"),

    MENU(OWNER, "d70d873b-8c46-4ee1-997b-b2bfa568e4a0"),
    MENU_READ(MENU, "1d081984-a9fe-4626-86e6-d128d968837a"),
    MENU_CREATE(MENU, "c4a730ac-c8e8-4ec3-b251-c4bad1fa352c"),
    MENU_MODIFY(MENU, "972c28c0-fd88-4427-a440-6dd7095ac40e"),
    MENU_PUBLISH(MENU, "c3cad89f-46f5-423d-8cd5-83224368ae12"),
    MENU_RESTORE(MENU, "a2628d4e-b231-495a-b188-d3c8d1062b4e"),

    ORDER(OWNER, "d834be96-8972-4662-86a2-a68c4a98f3ad"),
    ORDER_READ(ORDER, "5c18e98c-a832-4599-81d9-886701f928b5"),
    ORDER_CREATE(ORDER, "9902c0c3-267c-490e-9871-0bc40c9ea24e"),
    ORDER_MODIFY(ORDER, "");

    private final Permission parent;
    private final UUID externalId;

    Permission(Permission parent, String externalIdText) {
        this.parent = parent;
        this.externalId = UUID.fromString(externalIdText);
    }

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
