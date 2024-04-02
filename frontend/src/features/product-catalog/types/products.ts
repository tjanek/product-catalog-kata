export interface Product {
    id: string;
    name: string;
    price: number;
}

export interface NewProduct {
    name?: string | null;
    initialPrice?: string | null;
}