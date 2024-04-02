import {describe, expect, test} from 'vitest';
import {render, screen} from '@testing-library/react';
import {ProductCatalog} from "@/features/product-catalog";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClient = new QueryClient();

const getProductCatalog = async () => Promise.resolve([
    {
        id: "uuid-123",
        name: "Product 1",
        price: 19.99
    }
])
const deleteProductInCatalog = async () => Promise.resolve(null)
const addProductToCatalog = async () => Promise.resolve(null)

describe('ProductCatalog', () => {
    test('should render list of products', () => {
        render(
            <QueryClientProvider client={queryClient}>
                <ProductCatalog
                    getProductsFn={getProductCatalog}
                    addProductFn={addProductToCatalog}
                    deleteProductFn={deleteProductInCatalog}
                />
            </QueryClientProvider>
        );

        screen.debug();

        const loading = screen.getByText('Loading...');
        expect(loading).toBeDefined();
    })
})