import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table.tsx";
import {useQuery} from "@tanstack/react-query";
import {Product} from ".././types/products.ts";
import { getProducts } from "../api/api.product.ts"
import {Button} from "@/components/ui/button.tsx";
import {Badge} from "@/components/ui/badge.tsx";

export function ProductCatalog() {

    const { isLoading, data} = useQuery<Product[], Error>(
        {
            queryKey: ["product-catalog"],
            queryFn: () => getProducts(),
        })

    return (
        <>
            {isLoading ? (
                <Badge>Loading...</Badge>
            ) : (
                <>
                    <Table>
                        <TableCaption>Product catalog.</TableCaption>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Name</TableHead>
                                <TableHead className="text-right">Price</TableHead>
                                <TableHead className="text-right">Actions</TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {(data ?? []).length > 0 &&
                                (data ?? []).map((product) => (
                                    <TableRow key={product.id}>
                                        <TableCell >{product.name}</TableCell>
                                        <TableCell className="text-right">{product.price}</TableCell>
                                        <TableCell><Button variant="destructive">Delete</Button></TableCell>
                                    </TableRow>
                                ))}
                        </TableBody>
                    </Table>
                </>
            )}
        </>
    )

}