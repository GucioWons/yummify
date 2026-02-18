/* tslint:disable */
/* eslint-disable */

export namespace DTOs {

    export interface ApiErrorDTO {
        errorMessage: ErrorMessage;
        errorMessageString: string;
        properties: { [index: string]: any };
    }

    export interface ApiErrorResponseDTO {
        apiErrors: ApiErrorDTO[];
        errorOccurredAt: DateAsString;
        path: string;
    }

    export interface ErrorMessage {
        message: string;
    }

    export interface IngredientClientDTO {
        id: string;
        name: string;
    }

    export interface IngredientManageDTO {
        id: string;
        name: TranslatedStringDTO;
    }

    export interface PositionedDTO {
    }

    export interface RestaurantClientDTO {
        defaultLanguage: Language;
        description: string;
        id: string;
        name: string;
    }

    export interface RestaurantCreateDTO {
        owner: RestaurantOwnerDTO;
        restaurant: RestaurantManageDTO;
    }

    export interface RestaurantManageDTO {
        defaultLanguage: Language;
        description: TranslatedStringDTO;
        id: string;
        name: string;
    }

    export interface RestaurantOwnerDTO {
        email: string;
        firstName: string;
        lastName: string;
        username: string;
    }

    export interface TableDTO {
        id: string;
        name: string;
    }

    export interface TableOtpDTO {
        otp: string;
        tableId: string;
    }

    export interface TranslatedStringDTO {
        translations: { [index: string]: string };
    }

    export interface UserDTO {
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
