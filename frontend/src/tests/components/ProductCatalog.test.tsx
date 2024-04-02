import {describe, expect, test, vi} from 'vitest';
import {render, screen} from '@testing-library/react';
import {ProductCatalog} from "@/features/product-catalog";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClient = new QueryClient();

vi.mock("../api/api.product.ts", () => ({
    getProducts: () => Promise.resolve([
        {
            id: "uuid-123",
            name: "Product 1",
            price: 19.99
        }
    ]),
    deleteProduct: () => Promise.resolve(),
    addProduct: () => Promise.resolve()
}))

describe('ProductCatalog', () => {
    test('should render list of products', () => {
        render(
            <QueryClientProvider client={queryClient}>
                <ProductCatalog/>
            </QueryClientProvider>
        );

        screen.debug();

        const loading = screen.getByText('Loading...');
        expect(loading).toBeDefined();
    })
})