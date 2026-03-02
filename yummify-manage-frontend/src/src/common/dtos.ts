/* tslint:disable */
/* eslint-disable */

export namespace Dtos {

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

    export interface UserDto {
        email: string;
        firstName: string;
        id: string;
        lastName: string;
        restaurantId: string;
        username: string;
    }

    export type DateAsString = string;

    export enum Language {
        PL = "PL",
        EN = "EN",
        DE = "DE",
    }

}
