export interface MenuVersionClientDTO {
    sections: MenuSectionClientDTO[];
}

export interface MenuSectionClientDTO {
    id: string;
    position: number;
    name: string;
    entries: MenuEntryDTO[];
}

export interface MenuEntryDTO {
    id: string;
    dishId: string;
    price: number;
}