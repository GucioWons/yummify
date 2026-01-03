/* tslint:disable */
/* eslint-disable */

export namespace DTOs {

    export interface AdminTokenRequestDTO {
        client_id: string;
        grant_type: string;
        password: string;
        username: string;
    }

    export interface AdminTokenResponseDTO {
        access_token: string;
    }

    export interface ApiErrorDTO {
        errorLocationType: ErrorLocationType;
        errorMessage: ErrorMessage;
        errorMessageString: string;
        location: string;
    }

    export interface ApiErrorResponseDTO {
        apiErrors: ApiErrorDTO[];
        errorOccurredAt: DateAsString;
        path: string;
    }

    export interface BaseEntityDTO {
        id: string;
    }

    export interface DishClientDTO extends BaseEntityDTO {
        description: string;
        imageUrl: string;
        ingredients: IngredientClientDTO[];
        name: string;
    }

    export interface DishImageUrlDTO {
        imageUrl: string;
    }

    export interface DishListDTO extends BaseEntityDTO {
        name: string;
    }

    export interface DishManageDTO extends BaseEntityDTO {
        description: TranslatedStringDTO;
        imageUrl: string;
        ingredients: IngredientClientDTO[];
        name: TranslatedStringDTO;
    }

    export interface IngredientClientDTO extends BaseEntityDTO {
        name: string;
    }

    export interface IngredientListDTO extends BaseEntityDTO {
        name: string;
    }

    export interface IngredientManageDTO extends BaseEntityDTO {
        name: TranslatedStringDTO;
    }

    export interface MenuClientDTO extends BaseEntityDTO {
        sections: MenuSectionClientDTO[];
    }

    export interface MenuEntryDTO extends PositionedDTO {
        dish: DishClientDTO;
        price: number;
    }

    export interface MenuListDTO extends BaseEntityDTO {
    }

    export interface MenuManageDTO extends BaseEntityDTO {
        active: boolean;
        sections: MenuSectionManageDTO[];
    }

    export interface MenuSectionClientDTO extends PositionedDTO {
        entries: MenuEntryDTO[];
        name: string;
    }

    export interface MenuSectionManageDTO extends PositionedDTO {
        entries: MenuEntryDTO[];
        name: TranslatedStringDTO;
    }

    export interface OtpDTO {
        otp: string;
        username: string;
    }

    export interface PasswordRequestDTO {
        temporary: boolean;
        type: string;
        value: string;
    }

    export interface PositionedDTO extends BaseEntityDTO {
        position: number;
    }

    export interface RestaurantClientDTO extends BaseEntityDTO {
        defaultLanguage: Language;
        description: string;
        name: string;
    }

    export interface RestaurantCreateDTO {
        owner: UserRequestDTO;
        restaurant: RestaurantManageDTO;
    }

    export interface RestaurantManageDTO extends BaseEntityDTO {
        defaultLanguage: Language;
        description: TranslatedStringDTO;
        name: string;
    }

    export interface TableDTO extends BaseEntityDTO {
        name: string;
        capacity: number;
    }

    export interface TranslatedStringDTO {
        translations: { [P in Language]?: string };
    }

    export interface UserDTO {
        email: string;
        firstName: string;
        id: string;
        lastName: string;
        restaurantId: string;
        username: string;
    }

    export interface UserRequestDTO {
        attributes: { [index: string]: string[] };
        email: string;
        enabled: boolean;
        firstName: string;
        lastName: string;
        username: string;
    }

    export type DateAsString = string;


    export enum ErrorLocationType {
        PATH_PARAM = "PATH_PARAM",
        QUERY_PARAM = "QUERY_PARAM",
        HEADER_PARAM = "HEADER_PARAM",
        BODY = "BODY",
        AUTH = "AUTH",
        FILE = "FILE",
    }

    export enum ErrorMessage {
        RESTAURANT_NOT_FOUND_BY_ID = "RESTAURANT_NOT_FOUND_BY_ID",
        TABLE_EXISTS_BY_NAME = "TABLE_EXISTS_BY_NAME",
        TABLE_NOT_FOUND_BY_ID = "TABLE_NOT_FOUND_BY_ID",
        INGREDIENT_NOT_FOUND_BY_ID = "INGREDIENT_NOT_FOUND_BY_ID",
        INGREDIENTS_NOT_FOUND_BY_IDS = "INGREDIENTS_NOT_FOUND_BY_IDS",
        DISH_NOT_FOUND_BY_ID = "DISH_NOT_FOUND_BY_ID",
        MENU_NOT_FOUND_BY_ID = "MENU_NOT_FOUND_BY_ID",
        MENU_SECTION_NOT_FOUND_BY_ID = "MENU_SECTION_NOT_FOUND_BY_ID",
        MENU_ENTRY_NOT_FOUND_BY_ID = "MENU_ENTRY_NOT_FOUND_BY_ID",
        NO_ACTIVE_MENU = "NO_ACTIVE_MENU",
        MENU_IS_ACTIVE = "MENU_IS_ACTIVE",
        MENU_IS_NOT_ACTIVE = "MENU_IS_NOT_ACTIVE",
        FILE_NOT_FOUND_EXCEPTION = "FILE_NOT_FOUND_EXCEPTION",
        CANNOT_GET_FILE = "CANNOT_GET_FILE",
        KEYCLOAK_ACCOUNT_EXISTS_BY_EMAIL = "KEYCLOAK_ACCOUNT_EXISTS_BY_EMAIL",
        KEYCLOAK_ACCOUNT_EXISTS_BY_USERNAME = "KEYCLOAK_ACCOUNT_EXISTS_BY_USERNAME",
        ACCESS_DENIED = "ACCESS_DENIED",
        UNAUTHORIZED = "UNAUTHORIZED",
        VALIDATION_NOT_NULL = "VALIDATION_NOT_NULL",
        VALIDATION_NULL = "VALIDATION_NULL",
        UNEXPECTED_SERVER_ERROR = "UNEXPECTED_SERVER_ERROR",
    }

    export enum Language {
        PL = "PL",
        EN = "EN",
        DE = "DE",
    }

}
