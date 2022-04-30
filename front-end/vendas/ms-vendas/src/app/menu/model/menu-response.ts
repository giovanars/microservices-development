export interface MenuResponse {
    
}

export interface Menu {
    "id": string,
    "name": string,
    "description": string,
    "price": number,
    "createdAt": string
}

export type Menus = Array<Menu>;
