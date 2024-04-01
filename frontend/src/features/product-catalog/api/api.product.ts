import axios from "axios";
import {Product} from ".././types/products.ts";

const url = '/v1/products';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        "Content-type": "application/json",
    },
});

export const getProducts = async (): Promise<Product[]> => {
    return await api.get(url)
        .then((res) => res.data.products);
}

