import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table.tsx";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import {Product} from ".././types/products.ts";
import {deleteProduct, getProducts} from "../api/api.product.ts"
import {Button} from "@/components/ui/button.tsx";
import {Badge} from "@/components/ui/badge.tsx";
import {useSnackbar} from "notistack";
import {NewProduct} from "@/features/product-catalog/components/NewProduct.tsx";

export function ProductCatalog() {

    const queryClient = useQueryClient();
    const queryKey = "product-catalog";

    const { enqueueSnackbar } = useSnackbar();

    const { isLoading, data} = useQuery<Product[], Error>(
        {
            queryKey: [queryKey],
            queryFn: () => getProducts(),
        })

    const mutation = useMutation({
        mutationFn: deleteProduct,
        onSuccess: async () => {
            await queryClient.invalidateQueries({ queryKey: [queryKey] })
            enqueueSnackbar('Product was removed', {
                variant: 'success',
            });
        },
        onError: (error) => {
            const errorMessage = (error.message ?? 'Unknown error');
            enqueueSnackbar(`Error while deleting product: ${errorMessage}`, {
                variant: 'error'
            })
        }
    })

    return (
        <>
            {isLoading ? (
                <Badge>Loading...</Badge>
            ) : (
                <>
                    <Table>
                        <TableCaption><NewProduct queryKey={queryKey} /></TableCaption>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Name</TableHead>
                                <TableHead className="text-right">Price</TableHead>
                                <TableHead className="text-right"></TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {(data ?? []).length > 0 &&
                                (data ?? []).map((product) => (
                                    <TableRow key={product.id}>
                                        <TableCell >{product.name}</TableCell>
                                        <TableCell className="text-right">{product.price}</TableCell>
                                        <TableCell className="text-right"><Button variant="destructive" onClick={() => mutation.mutate(product.id)}>Delete</Button></TableCell>
                                    </TableRow>
                                ))}
                        </TableBody>
                    </Table>
                </>
            )}
        </>
    )

}
