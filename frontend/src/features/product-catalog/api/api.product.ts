import axios from "axios";
import {NewProduct, Product} from ".././types/products.ts";

const url = '/v1/products';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        "Content-type": "application/json",
    },
});

export const addProduct = async (product: NewProduct): Promise<null> => {
    return await api.post(url, product)
        .then(() => null);
}

export const getProducts = async (): Promise<Product[]> => {
    return await api.get(url)
        .then((res) => res.data.products);
}

export const deleteProduct = async (id: string) : Promise<null> => {
    return await api.delete(`${url}/${id}`)
        .then(() => null)
}

