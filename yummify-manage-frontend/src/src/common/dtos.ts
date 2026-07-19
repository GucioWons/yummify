/* tslint:disable */
/* eslint-disable */

export namespace Dtos {

    export interface AddOrderItemDto {
        dishId: string;
        quantity: number;
    }

    export interface ApiErrorDto {
        errorMessage: ErrorMessage;
        errorMessageString: string;
        properties: { [index: string]: any };
    }

    export interface ApiErrorResponseDto {
        apiErrors: ApiErrorDto[];
        errorOccurredAt: DateAsString;
        path: string;
    }

    export interface CreateMenuSectionRequest {
        name: TranslatedStringDto;
    }

    export interface CreateOrderDto {
        tableId: string;
    }

    export interface DishClientDto {
        description: string;
        id: string;
        imageUrl: string;
        ingredientIds: string[];
        name: string;
    }

    export interface DishImageUrlDto {
        imageUrl: string;
    }

    export interface DishListDto {
        id: string;
        name: string;
    }

    export interface DishManageDto {
        description: TranslatedStringDto;
        id: string;
        imageUrl: string;
        ingredientIds: string[];
        name: TranslatedStringDto;
    }

    export interface ErrorMessage {
        message: string;
    }

    export interface IngredientClientDto {
        id: string;
        name: string;
    }

    export interface IngredientManageDto {
        id: string;
        name: TranslatedStringDto;
    }

    export interface MenuEntryDto {
        dishId: string;
        id: string;
        price: number;
    }

    export interface MenuSectionClientDto extends PositionedDto {
        entries: MenuEntryDto[];
        id: string;
        name: string;
        position: number;
    }

    export interface MenuSectionManageDto extends PositionedDto {
        entries: MenuEntryDto[];
        id: string;
        name: TranslatedStringDto;
        position: number;
    }

    export interface MenuVersionArchivedListDto {
        archivedAt: DateAsString;
        id: string;
        version: number;
    }

    export interface MenuVersionClientDto {
        sections: MenuSectionClientDto[];
    }

    export interface MenuVersionManageDto {
        sections: MenuSectionManageDto[];
    }

    export interface OrderClientDto {
        id: string;
        items: OrderItemClientDto[];
        status: OrderStatus;
        tableId: string;
    }

    export interface OrderItemClientDto {
        dishId: string;
        id: string;
        name: string;
        price: number;
        quantity: number;
    }

    export interface PositionedDto {
    }

    export interface RestaurantClientDto {
        defaultLanguage: Language;
        description: string;
        id: string;
        name: string;
    }

    export interface RestaurantCreateDto {
        owner: RestaurantOwnerDto;
        restaurant: RestaurantManageDto;
    }

    export interface RestaurantManageDto {
        defaultLanguage: string;
        description: TranslatedStringDto;
        id: string;
        name: string;
    }

    export interface RestaurantOwnerDto {
        email: string;
        firstName: string;
        lastName: string;
        username: string;
    }

    export interface RoleListDto {
        id: string;
        name: string;
    }

    export interface RoleManageDto {
        id: string;
        name: TranslatedStringDto;
        permissions: string[];
    }

    export interface TableDto {
        capacity: number;
        id: string;
        name: string;
    }

    export interface TableOtpDto {
        otp: string;
        tableId: string;
    }

    export interface TranslatedStringDto {
        translations: { [index: string]: string };
    }

    export interface UpdateMenuSectionEntriesRequest {
        entries: MenuEntryDto[];
    }

    export interface UpdateMenuSectionNameRequest {
        name: TranslatedStringDto;
    }

    export interface UpdateMenuSectionPositionRequest {
        position: number;
    }

    export interface UserDto {
        email: string;
        firstName: string;
        id: string;
        lastName: string;
        permissions: string[];
        restaurantId: string;
        username: string;
    }

    export interface UserManageDto {
        email: string;
        firstName: string;
        id: string;
        lastName: string;
        roleId: string;
        username: string;
    }

    export type DateAsString = string;

    export enum Language {
        PL = "PL",
        EN = "EN",
        DE = "DE",
    }

    export enum OrderStatus {
        NEW = "NEW",
        CONFIRMED = "CONFIRMED",
        IN_PREPARATION = "IN_PREPARATION",
        DELIVERED = "DELIVERED",
        COMPLETED = "COMPLETED",
        CANCELLED = "CANCELLED",
    }

    export enum Permission {
        ADMIN = "ADMIN",
        OWNER = "OWNER",
        USER = "USER",
        USER_READ = "USER_READ",
        USER_CREATE = "USER_CREATE",
        ROLE = "ROLE",
        ROLE_READ = "ROLE_READ",
        ROLE_CREATE = "ROLE_CREATE",
        RESTAURANT = "RESTAURANT",
        RESTAURANT_MODIFY = "RESTAURANT_MODIFY",
        TABLE = "TABLE",
        TABLE_READ = "TABLE_READ",
        TABLE_CREATE = "TABLE_CREATE",
        TABLE_MODIFY = "TABLE_MODIFY",
        TABLE_GENERATE_OTP = "TABLE_GENERATE_OTP",
        INGREDIENT = "INGREDIENT",
        INGREDIENT_READ = "INGREDIENT_READ",
        INGREDIENT_CREATE = "INGREDIENT_CREATE",
        INGREDIENT_MODIFY = "INGREDIENT_MODIFY",
        DISH = "DISH",
        DISH_READ = "DISH_READ",
        DISH_CREATE = "DISH_CREATE",
        DISH_MODIFY = "DISH_MODIFY",
        MENU = "MENU",
        MENU_READ = "MENU_READ",
        MENU_CREATE = "MENU_CREATE",
        MENU_MODIFY = "MENU_MODIFY",
        MENU_PUBLISH = "MENU_PUBLISH",
        MENU_RESTORE = "MENU_RESTORE",
        ORDER = "ORDER",
        ORDER_READ = "ORDER_READ",
        ORDER_CREATE = "ORDER_CREATE",
        ORDER_MODIFY = "ORDER_MODIFY",
    }

}
