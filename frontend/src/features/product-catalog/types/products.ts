export interface Product {
    id: string;
    name: string;
    price: number;
}

export interface NewProduct {
    name?: string | null;
    initialPrice?: string | null;
}

export type GetProductsType = () => Promise<Product[]>;
export type AddProductType = (product: NewProduct) => Promise<null>;
export type DeleteProductType = (id: string) => Promise<null>;
