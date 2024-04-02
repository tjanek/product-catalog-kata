import {useMutation, useQueryClient} from "@tanstack/react-query";
import {Button} from "@/components/ui/button.tsx";
import {useSnackbar} from "notistack";
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger
} from "@/components/ui/dialog.tsx";
import {Label} from "@/components/ui/label.tsx";
import {Input} from "@/components/ui/input.tsx";
import {useState} from "react";
import {addProduct} from "../api/api.product.ts";
import {AddProductType} from "../types/products.ts";

interface NewProductProps {
    queryKey: string;
    addProductFn?: AddProductType;
}

export function NewProduct({ queryKey, addProductFn }: NewProductProps): JSX.Element {

    const [newProductName, setNewProductName] = useState("");
    const [newProductPrice, setNewProductPrice] = useState("");
    const [open, setOpen] = useState(false);

    const queryClient = useQueryClient();

    addProductFn = addProductFn ?? addProduct;

    const { enqueueSnackbar } = useSnackbar();

    const { status, mutate } = useMutation({
        mutationFn: addProductFn,
        onSuccess: async () => {
            await queryClient.invalidateQueries({ queryKey: [queryKey] })
            setNewProductName("")
            setNewProductPrice("")
            setOpen(false)
            enqueueSnackbar('Product was added', {
                variant: 'success',
            });
        },
        onError: (error) => {
            const errorMessage = (error.message ?? 'Unknown error');
            enqueueSnackbar(`Error while adding new product: ${errorMessage}`, {
                variant: 'error'
            })
        }
    })

    const isButtonDisabled =
        newProductName === "" ||
        newProductPrice === "" ||
        status === "pending"

    return (
        <>
            <Dialog open={open} onOpenChange={(open: boolean) => {setOpen(open); setNewProductName(""); setNewProductPrice("");}}>
                <DialogTrigger asChild>
                    <Button variant="outline">Add product</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-[425px]">
                    <DialogHeader>
                        <DialogTitle>Add new product</DialogTitle>
                        <DialogDescription>
                            Click confirm when you're done.
                        </DialogDescription>
                    </DialogHeader>
                    <div className="grid gap-4 py-4">
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="name" className="text-right">
                                Name
                            </Label>
                            <Input
                                id="name"
                                className="col-span-3"
                                value={newProductName}
                                onChange={(e) => setNewProductName(e.target.value)}
                            />
                        </div>
                        <div className="grid grid-cols-4 items-center gap-4">
                            <Label htmlFor="price" className="text-right">
                                Price
                            </Label>
                            <Input
                                id="price"
                                className="col-span-3"
                                value={newProductPrice}
                                onChange={(e) => setNewProductPrice(e.target.value)}
                            />
                        </div>
                    </div>
                    <DialogFooter>
                        <Button type="submit" disabled={isButtonDisabled} onClick={() => mutate({ name: newProductName, initialPrice: newProductPrice })}>Confirm</Button>
                    </DialogFooter>
                </DialogContent>
            </Dialog>
        </>
    )

}
